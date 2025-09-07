package com.example.sampleproject.features.connection.domain.repository

import com.example.sampleproject.features.connection.domain.model.ConnectionConfig

interface ConnectionRepository {
    suspend fun saveConfig(config: ConnectionConfig)
    suspend fun getConfig(): ConnectionConfig

    suspend fun saveIp(ip: String)
    suspend fun getIp(): String

    suspend fun savePort(port: Int)
    suspend fun getPort(): Int

    suspend fun saveNii(nii: Int)
    suspend fun getNii(): Int
}
