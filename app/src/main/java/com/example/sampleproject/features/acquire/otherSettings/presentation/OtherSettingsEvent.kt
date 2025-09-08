package com.example.sampleproject.features.acquire.otherSettings.presentation

sealed class OtherSettingsEvent {
    data object NavigateToConfig : OtherSettingsEvent()
}
