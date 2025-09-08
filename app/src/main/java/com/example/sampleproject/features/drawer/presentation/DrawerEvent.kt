package com.example.sampleproject.features.drawer.presentation

sealed class DrawerEvent {
    data object NavigateToSupport : DrawerEvent()
    data object NavigateToAcquire : DrawerEvent()
    data object NavigateToInfo : DrawerEvent()
}
