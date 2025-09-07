package com.example.sampleproject.features.connection.presentation.contract

import com.example.sampleproject.features.connection.domain.model.ConnectionConfig

sealed class ConnectionIntent {
    data class SaveConfig(val config: ConnectionConfig) : ConnectionIntent()
    data class SaveIp(val ip: String) : ConnectionIntent()
    data class SavePort(val port: Int) : ConnectionIntent()
    data class SaveNii(val nii: Int) : ConnectionIntent()
}
