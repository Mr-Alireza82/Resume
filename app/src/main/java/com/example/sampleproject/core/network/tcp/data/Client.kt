package com.example.sampleproject.core.network.tcp.data

import com.example.sampleproject.core.network.tcp.extension.suspendConnect
import com.example.sampleproject.core.network.tcp.extension.suspendRead
import com.example.sampleproject.core.network.tcp.extension.suspendWrite
import com.example.sampleproject.core.network.tcp.presentation.contract.ConnectionState
import com.example.sampleproject.core.network.tcp.presentation.contract.ReadState
import com.example.sampleproject.core.network.tcp.presentation.contract.WriteState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel

class Client internal constructor(
    private val ip: String,
    private val port: Int,
    private val buffer: ByteArray,
    private val timeoutMillis: Long,
    private var retryCount: Int,
    private val sslEnabled: Boolean,
    private val responseLength: Pair<Boolean, Int>?,
    private val chunkEnabled: Boolean
) {

    private var socket: AsynchronousSocketChannel? = null

    fun connect(): Flow<ConnectionState> = channelFlow {
        var attempt = 0
        var connected = false

        send(ConnectionState.Connecting)

        while (attempt <= retryCount && !connected) {
            try {
                withContext(Dispatchers.IO) {
                    withTimeout(timeoutMillis) {
                        val channel = AsynchronousSocketChannel.open()
                        val address = InetSocketAddress(ip, port)

                        channel.suspendConnect(address)

                        socket = channel

                        connected = true
                    }
                }

                if (connected) {
                    send(ConnectionState.ConnectSuccess)
                    break
                }

            } catch (e: Exception) {
                if (attempt == retryCount) {
                    send(ConnectionState.ConnectFailed("Max retries reached: ${e.message}"))
                    disconnect()
                    break
                } else {
                    send(ConnectionState.Reconnecting(attempt + 1))
                    delay(500L)
                }
            }

            attempt++
        }
    }

    fun write(): Flow<WriteState> = channelFlow {
        val currentSocket = socket
        if (currentSocket == null || !currentSocket.isOpen) {
            send(WriteState.WriteFailed("Socket not connected"))
            return@channelFlow
        }

        var attempt = 0
        var written = false

        send(WriteState.Writing)

        while (attempt <= retryCount && !written) {
            try {
                withContext(Dispatchers.IO) {
                    withTimeout(timeoutMillis) {
                        val bufferToSend = ByteBuffer.wrap(buffer)

                        while (bufferToSend.hasRemaining()) {
                            currentSocket.suspendWrite(bufferToSend)
                        }

                        written = true
                    }
                }

                if (written) {
                    send(WriteState.WriteSuccess)
                    break
                }

            } catch (e: Exception) {
                if (attempt == retryCount) {
                    send(WriteState.WriteFailed("Write failed: ${e.message}"))
                    disconnect()
                    break
                } else {
                    send(WriteState.ReWriting(attempt + 1))
                }
            }

            attempt++
        }
    }

    fun read(): Flow<ReadState> = channelFlow {
        val currentSocket = socket
        if (currentSocket == null || !currentSocket.isOpen) {
            send(ReadState.ReadFailed("Socket not connected"))
            return@channelFlow
        }

        var attempt = 0
        var read = false

        send(ReadState.Reading)

        while (attempt <= retryCount && !read) {
            try {
                val responseBytes = withContext(Dispatchers.IO) {
                    withTimeout(timeoutMillis) {
                        val buffer = ByteBuffer.allocate(1024)
                        val output = mutableListOf<Byte>()

                        while (true) {
                            val bytesRead = currentSocket.suspendRead(buffer)
                            if (bytesRead == -1) break

                            buffer.flip()
                            repeat(buffer.remaining()) {
                                output.add(buffer.get())
                            }
                            buffer.clear()

                            // stop if server sent expected length
                            if (responseLength?.first == true && output.size >= responseLength.second) break

                            // stop if chunk mode is disabled (single read)
                            if (!chunkEnabled) break
                        }

                        output.toByteArray()
                    }
                }

                println("📥 Received [${responseBytes.size} bytes]: ${responseBytes.joinToString("") { "%02X".format(it) }}")

                send(ReadState.ReadSuccess(responseBytes))
                read = true

            } catch (e: Exception) {
                if (attempt == retryCount) {
                    send(ReadState.ReadFailed("Read failed: ${e.message}"))
                    disconnect()
                    break
                } else {
                    send(ReadState.ReReading(attempt + 1))
                    delay(500L)
                }
            }

            attempt++
        }
    }

    fun disconnect() {
        try {
            socket?.close()
            socket = null
            println("🔌 Disconnected from $ip:$port")
        } catch (ex: Exception) {
            println("⚠️ Error while disconnecting: ${ex.message}")
        }
    }
}
