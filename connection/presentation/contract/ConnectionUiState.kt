package com.example.sampleproject.features.connection.presentation.contract

import com.example.sampleproject.features.connection.domain.model.ConnectionConfig

sealed class ConnectionUiState {
    data object Loading : ConnectionUiState()
    data class Success(val config: ConnectionConfig) : ConnectionUiState()
    data class Error(val message: String) : ConnectionUiState()
}
