package com.example.sampleproject.features.keys.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.features.keys.domain.model.KnownApplet
import com.example.sampleproject.features.keys.domain.usecase.GetSelectedAppletUseCase
import com.example.sampleproject.features.keys.domain.usecase.UpdateSelectedAppletUseCase
import com.example.sampleproject.features.keys.presentation.contract.AppletEffect
import com.example.sampleproject.features.keys.presentation.contract.AppletState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyViewModel @Inject constructor(
    private val getSelectedAppletUseCase: GetSelectedAppletUseCase,
    private val updateSelectedAppletUseCase: UpdateSelectedAppletUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AppletState>(AppletState.Loading)
    val state: StateFlow<AppletState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AppletEffect>()
    val effect = _effect.asSharedFlow()

    private val all = KnownApplet.entries

    init {
        viewModelScope.launch {
            // load initial selection from data store
            try {
                val selected = getSelectedAppletUseCase().first()
                _state.value = AppletState.Success(selected = selected, all = all)
            } catch (t: Throwable) {
                _state.value = AppletState.Error(t.message ?: "Unknown")
            }
        }
    }

    fun onSelect(applet: KnownApplet) {
        viewModelScope.launch {
            updateSelectedAppletUseCase(applet)
            _effect.emit(AppletEffect.AppletChanged(applet))
            // re-emit state with selected first
            _state.value = AppletState.Success(selected = applet, all = listOf(applet) + all.filter { it != applet })
        }
    }
}
