package com.example.sampleproject.features.connection.data.repository

import com.example.sampleproject.features.connection.data.local.ConnectionDao
import com.example.sampleproject.features.connection.data.local.ConnectionEntity
import com.example.sampleproject.features.connection.domain.model.ConnectionConfig
import com.example.sampleproject.features.connection.domain.repository.ConnectionRepository

class ConnectionRepositoryImpl(
    private val dao: ConnectionDao
) : ConnectionRepository {

    override suspend fun saveConfig(config: ConnectionConfig) {
        val entity = ConnectionEntity(
            id = 1,
            ip = config.ip,
            port = config.port,
            nii = config.nii
        )

        dao.insert(entity)
    }

    override suspend fun getConfig(): ConnectionConfig {
        val entity = dao.get()

        return ConnectionConfig(
            ip = entity?.ip ?: "78.157.33.208",
            port = entity?.port ?: 4142,
            nii = entity?.nii ?: 85
        )
    }

    override suspend fun saveIp(ip: String) = dao.updateIp(ip)

    override suspend fun getIp(): String = dao.getIp() ?: "78.157.33.208"

    override suspend fun savePort(port: Int) = dao.updatePort(port)

    override suspend fun getPort(): Int = dao.getPort() ?: 4142

    override suspend fun saveNii(nii: Int) = dao.updateNii(nii)

    override suspend fun getNii(): Int = dao.getNii() ?: 85
}
