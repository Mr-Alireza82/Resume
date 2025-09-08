package com.example.sampleproject.features.startup.presentation.splash.contract

object SplashContract {
    sealed class Intent {
        data object StartInitialization : Intent()
    }

    sealed class UiState {
        data object Loading : UiState()
        data object Success : UiState()
        data class Error(val message: String) : UiState()
    }

    sealed class Event {
        data object NavigateToMain : Event()
    }
}
