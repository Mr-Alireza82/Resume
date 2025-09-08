package com.example.sampleproject.features.terminal.domain

interface TerminalRepository {
    suspend fun saveMacKey(macKey: ByteArray)
    suspend fun getMacKey(): ByteArray?
}
