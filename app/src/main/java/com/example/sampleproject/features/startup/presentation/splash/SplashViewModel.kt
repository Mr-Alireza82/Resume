package com.example.sampleproject.features.startup.presentation.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.features.startup.domain.repository.InitializeAppUseCase
import com.example.sampleproject.features.startup.presentation.splash.contract.SplashContract.Event
import com.example.sampleproject.features.startup.presentation.splash.contract.SplashContract.Intent
import com.example.sampleproject.features.startup.presentation.splash.contract.SplashContract.UiState
import com.example.sampleproject.features.terminal.domain.TerminalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val initializeAppUseCase: InitializeAppUseCase,
    private val terminalRepository: TerminalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    fun processIntent(intent: Intent) {
        when (intent) {
            is Intent.StartInitialization -> initializeApp()
        }
    }

    private fun initializeApp() {
        viewModelScope.launch {
            try {
                initializeAppUseCase()
                reduce(UiState.Success)
                Log.d("SplashInitKey1", "Initialization succeeded")

                val macKey = terminalRepository.getMacKey()
                Log.d("SplashInitKey2", "MacKey: $macKey")

                //delay(5000)

                _event.emit(Event.NavigateToMain)

            } catch (e: Exception) {
                Log.e("SplashInitKe3", "Initialization failed", e)
                reduce(UiState.Error("Init failed: ${e.message}"))
            }
        }
    }

    private fun reduce(newState: UiState) {
        _uiState.value = newState
    }
}
