package com.example.sampleproject.features.connection.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.features.connection.domain.model.ConnectionConfig
import com.example.sampleproject.features.connection.domain.usecase.ConnectionUseCase
import com.example.sampleproject.features.connection.presentation.contract.ConnectionIntent
import com.example.sampleproject.features.connection.presentation.contract.ConnectionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConnectionViewModel @Inject constructor(
    private val useCase: ConnectionUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ConnectionUiState>(ConnectionUiState.Loading)
    val uiState: StateFlow<ConnectionUiState> = _uiState

    init {
        loadConfig()
    }

    private fun loadConfig() {
        viewModelScope.launch {
            try {
                val config = useCase.getConfig()
                _uiState.value = ConnectionUiState.Success(
                    ConnectionConfig(config.ip, config.port, config.nii)
                )
            } catch (e: Exception) {
                _uiState.value = ConnectionUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun processIntent(intent: ConnectionIntent) {
        when (intent) {
            is ConnectionIntent.SaveConfig -> saveConfigToDb(intent.config)
            is ConnectionIntent.SaveIp -> saveIpToDb(intent.ip)
            is ConnectionIntent.SavePort -> savePortToDb(intent.port)
            is ConnectionIntent.SaveNii -> saveNiiToDb(intent.nii)
        }
    }

    private fun saveConfigToDb(config: ConnectionConfig) {
        viewModelScope.launch {
            useCase.saveConfig(config)
            _uiState.value = ConnectionUiState.Success(config)
        }
    }

    private fun saveIpToDb(ip: String) {
        viewModelScope.launch {
            useCase.saveIp(ip)
            val current = (uiState.value as? ConnectionUiState.Success)?.config
            current?.let {
                _uiState.value = ConnectionUiState.Success(it.copy(ip = ip))
            }
        }
    }

    private fun savePortToDb(port: Int) {
        viewModelScope.launch {
            useCase.savePort(port)
            val current = (uiState.value as? ConnectionUiState.Success)?.config
            current?.let {
                _uiState.value = ConnectionUiState.Success(it.copy(port = port))
            }
        }
    }

    private fun saveNiiToDb(nii: Int) {
        viewModelScope.launch {
            useCase.saveNii(nii)
            val current = (uiState.value as? ConnectionUiState.Success)?.config
            current?.let {
                _uiState.value = ConnectionUiState.Success(it.copy(nii = nii))
            }
        }
    }
}