package com.example.sampleproject.features.support.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.core.iso.iso8583.client.TCP
import com.example.sampleproject.core.iso.iso8583.message.domain.entity.IsoMessagePacker
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.builders.SipaProtocolBuilder
import com.example.sampleproject.core.network.tcp.data.Client
import com.example.sampleproject.core.network.tcp.data.TCPViewModelImpl
import com.example.sampleproject.core.network.tcp.presentation.contract.ConnectionState
import com.example.sampleproject.core.network.tcp.presentation.contract.ReadState
import com.example.sampleproject.core.network.tcp.presentation.contract.WriteState
import com.example.sampleproject.core.utils.converter.byteArrayToHex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SupportViewModel : TCPViewModelImpl() {

    private val _connectionState = MutableStateFlow<ConnectionState?>(null)
    val connectionState: StateFlow<ConnectionState?> = _connectionState

    private val _writeState = MutableStateFlow<WriteState?>(null)
    val writeState: StateFlow<WriteState?> = _writeState

    private val _readState = MutableStateFlow<ReadState?>(null)
    val readState: StateFlow<ReadState?> = _readState

    override fun startTcpSession() {
        viewModelScope.launch {

            val protocol = SipaProtocolBuilder.from("0800", "920000").build()

            val message = IsoMessagePacker().setMessage(protocol.getMessage()).build()

            val client = TCP.create()
                .config("78.157.33.210", 2150)
                .buffer(message.message)
                .timeout(3000L, TimeUnit.MILLISECONDS)
                .retryCount(3)
                .enableSSL(false)
                .hasResponseLength(true, 4)
                .enableChunkResponse(true)
                .build()

            client.connect()
                .distinctUntilChangedBy { it::class }
                .collect { state ->

                    if (_connectionState.value?.javaClass != state.javaClass) {
                        _connectionState.value = state
                    }

                    if (state is ConnectionState.ConnectSuccess) {
                        writeToTcp(client)
                    } else if (state is ConnectionState.ConnectFailed) {
                        client.disconnect()
                    }
                }
        }
    }

    override fun writeToTcp(client: Client) {
        viewModelScope.launch {
            client.write().collect { state ->
                _writeState.value = state
                Log.i("SupportVM", "Write state: $state")

                if (state is WriteState.WriteSuccess) {
                    Log.i("SupportVM", "Write successful, now starting read ✅")
                    readFromTcp(client) // start reading only after write success
                }
            }
        }
    }

    override fun readFromTcp(client: Client) {
        viewModelScope.launch {
            client.read().collect { state ->
                _readState.value = state
                Log.i("SupportVM", "Read state: $state")

                when (state) {
                    is ReadState.ReadSuccess -> {
                        Log.i("SupportVM", "Response: ${state.response.byteArrayToHex()}")
                        // TODO: pass to ISO parser
                    }

                    is ReadState.ReadFailed -> {
                        Log.e("SupportVM", "Read failed ❌, disconnecting")
                        client.disconnect()
                    }

                    else -> {}
                }
            }
        }
    }
}
