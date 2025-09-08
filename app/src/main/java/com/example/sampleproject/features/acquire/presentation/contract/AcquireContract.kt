package com.example.sampleproject.features.acquire.presentation.contract

sealed class AcquireEvent {
    data object FontStyle : AcquireEvent()
    data object OtherSettings : AcquireEvent()
}
