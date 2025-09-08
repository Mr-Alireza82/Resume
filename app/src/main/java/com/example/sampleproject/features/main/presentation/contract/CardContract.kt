package com.example.sampleproject.features.main.presentation.contract

import com.example.sampleproject.features.main.domain.model.CardItems

sealed interface CardState {
    object Loading : CardState
    object Error : CardState
    object Success : CardState
}

sealed interface CardEffect {
    data class CardClicked(val language: CardItems) : CardEffect
}
