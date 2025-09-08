package com.example.sampleproject.features.startup.domain.repository

import com.example.sampleproject.features.terminal.domain.TerminalRepository

class InitializeAppUseCase(
    private val macKey: ByteArray,
    private val terminalRepository: TerminalRepository
) {
    suspend operator fun invoke() {
        terminalRepository.saveMacKey(macKey)
    }
}
