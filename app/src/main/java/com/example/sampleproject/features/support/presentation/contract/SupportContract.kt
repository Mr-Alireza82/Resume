package com.example.sampleproject.features.support.presentation.contract

sealed class SupportEvent {
    data object ConnectionSettings : SupportEvent()
    data object Mac800 : SupportEvent()
    data object GetAuthorizationKeys : SupportEvent()
    data object GetConfiguration : SupportEvent()
    data object UploadMasterKey : SupportEvent()
    data object ResetSupportPassword : SupportEvent()
    data object Exit : SupportEvent()
}
