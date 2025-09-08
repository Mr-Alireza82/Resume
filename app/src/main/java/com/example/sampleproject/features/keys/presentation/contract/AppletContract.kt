package com.example.sampleproject.features.keys.presentation.contract

import com.example.sampleproject.features.keys.domain.model.KnownApplet

sealed interface AppletState {
    object Loading : AppletState
    data class Success(val selected: KnownApplet, val all: List<KnownApplet>) : AppletState
    data class Error(val message: String) : AppletState
}

sealed interface AppletEffect {
    data class AppletChanged(val applet: KnownApplet) : AppletEffect
}
