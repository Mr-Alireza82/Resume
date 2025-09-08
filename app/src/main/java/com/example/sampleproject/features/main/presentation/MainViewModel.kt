package com.example.sampleproject.features.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.features.main.data.mapper.CardItemsMapper
import com.example.sampleproject.features.main.domain.model.CardMetadata
import com.example.sampleproject.features.main.presentation.contract.CardEffect
import com.example.sampleproject.features.main.presentation.contract.CardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow<CardState>(CardState.Loading)
    val state: StateFlow<CardState> = _state.asStateFlow()

    private val _cards = MutableStateFlow<List<CardMetadata>>(emptyList())
    val cards: StateFlow<List<CardMetadata>> = _cards.asStateFlow()

    private val _effect = MutableSharedFlow<CardEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            _cards.value = CardItemsMapper.allItems()
            _state.value = CardState.Success
        }
    }
}
