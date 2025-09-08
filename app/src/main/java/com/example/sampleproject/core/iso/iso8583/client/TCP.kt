package com.example.sampleproject.core.iso.iso8583.client

import com.example.sampleproject.core.network.tcp.data.Client
import java.util.concurrent.TimeUnit

class TCP private constructor() {

    private var ip: String? = null
    private var port: Int? = null
    private var buffer: ByteArray? = null
    private var timeoutMillis: Long? = null
    private var retryCount: Int? = null
    private var enableSSL: Boolean? = null
    private var hasResponseLength: Pair<Boolean, Int>? = null
    private var enableChunk: Boolean? = null

    private val calledMethods = mutableSetOf<RequiredStep>()

    enum class RequiredStep {
        CONFIG,
        BUFFER,
        TIMEOUT,
        RETRY_COUNT,
        ENABLE_SSL,
        RESPONSE_LENGTH,
        CHUNK_RESPONSE
    }

    companion object {
        private val REQUIRED_STEPS = RequiredStep.entries.toSet()

        fun create(): com.example.sampleproject.core.iso.iso8583.client.TCP = TCP()
    }

    fun config(ip: String, port: Int): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.ip = ip
        this.port = port
        calledMethods += RequiredStep.CONFIG
        return this
    }

    fun buffer(msg: ByteArray): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.buffer = msg
        calledMethods += RequiredStep.BUFFER
        return this
    }

    fun timeout(duration: Long, unit: TimeUnit): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.timeoutMillis = unit.toMillis(duration)
        calledMethods += RequiredStep.TIMEOUT
        return this
    }

    fun retryCount(count: Int): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.retryCount = count
        calledMethods += RequiredStep.RETRY_COUNT
        return this
    }

    fun enableSSL(required: Boolean): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.enableSSL = required
        calledMethods += RequiredStep.ENABLE_SSL
        return this
    }

    fun hasResponseLength(required: Boolean, length: Int): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.hasResponseLength = Pair(required, length)
        calledMethods += RequiredStep.RESPONSE_LENGTH
        return this
    }

    fun enableChunkResponse(enable: Boolean): com.example.sampleproject.core.iso.iso8583.client.TCP {
        this.enableChunk = enable
        calledMethods += RequiredStep.CHUNK_RESPONSE
        return this
    }

    fun build(): Client {
        val missing = REQUIRED_STEPS - calledMethods
        if (missing.isNotEmpty()) {
            throw IllegalStateException("Missing configuration steps: ${missing.joinToString()}")
        }

        return Client(
            ip!!,
            port!!,
            buffer!!,
            timeoutMillis!!,
            retryCount!!,
            enableSSL == true,
            hasResponseLength!!,
            enableChunk == true
        )
    }
}