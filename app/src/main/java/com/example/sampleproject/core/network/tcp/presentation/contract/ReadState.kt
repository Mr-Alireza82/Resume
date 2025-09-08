package com.example.sampleproject.core.network.tcp.presentation.contract

sealed class ReadState {
    object Reading : ReadState()
    data class ReReading(val attempt: Int) : ReadState()
    data class ReadSuccess(val response: ByteArray) : ReadState() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as ReadSuccess

            return response.contentEquals(other.response)
        }

        override fun hashCode(): Int {
            return response.contentHashCode()
        }
    }
    data class ReadFailed(val reason: String) : ReadState()
}
