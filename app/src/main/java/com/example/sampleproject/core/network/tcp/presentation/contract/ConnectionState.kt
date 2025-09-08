package com.example.sampleproject.core.network.tcp.presentation.contract

sealed class ConnectionState {
    object Connecting : ConnectionState()
    data class Reconnecting(val attempt: Int) : ConnectionState()
    object ConnectSuccess : ConnectionState()
    data class ConnectFailed(val reason: String) : ConnectionState()
}
