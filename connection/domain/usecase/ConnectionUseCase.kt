package com.example.sampleproject.features.connection.domain.usecase

import com.example.sampleproject.features.connection.domain.model.ConnectionConfig
import com.example.sampleproject.features.connection.domain.repository.ConnectionRepository

class ConnectionUseCase(
    private val repository: ConnectionRepository
) {

    suspend fun saveConfig(config: ConnectionConfig) {
        repository.saveConfig(config)
    }

    suspend fun getConfig(): ConnectionConfig {
        return repository.getConfig()
    }

    suspend fun saveIp(ip: String) {
        repository.saveIp(ip)
    }

    suspend fun getIp(): String {
        return repository.getIp()
    }

    suspend fun savePort(port: Int) {
        repository.savePort(port)
    }

    suspend fun getPort(): Int {
        return repository.getPort()
    }

    suspend fun saveNii(nii: Int) {
        repository.saveNii(nii)
    }

    suspend fun getNii(): Int {
        return repository.getNii()
    }
}
