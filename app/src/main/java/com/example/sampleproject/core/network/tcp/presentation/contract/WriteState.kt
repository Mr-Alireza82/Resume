package com.example.sampleproject.core.network.tcp.presentation.contract

sealed class WriteState {
    object Writing : WriteState()
    data class ReWriting(val attempt: Int) : WriteState()
    object WriteSuccess : WriteState()
    data class WriteFailed(val reason: String) : WriteState()
}
