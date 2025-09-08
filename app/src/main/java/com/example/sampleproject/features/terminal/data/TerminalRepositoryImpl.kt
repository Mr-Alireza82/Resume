package com.example.sampleproject.features.terminal.data

import com.example.sampleproject.core.database.local.terminal.data.TerminalDao
import com.example.sampleproject.core.database.local.terminal.data.TerminalEntity
import com.example.sampleproject.features.terminal.domain.TerminalRepository

class TerminalRepositoryImpl(
    private val dao: TerminalDao
) : TerminalRepository {

    override suspend fun saveMacKey(macKey: ByteArray) {
        val existing = dao.get()
        if (existing == null) {
            dao.insert(TerminalEntity(id = 1, macKey = macKey))
        } else {
            dao.updateMacKey(macKey)
        }
    }

    override suspend fun getMacKey(): ByteArray? {
        return dao.getMacKey()
    }
}
