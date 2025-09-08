package com.example.sampleproject.core.network.tcp.extension

import kotlinx.coroutines.suspendCancellableCoroutine
import java.nio.ByteBuffer
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun AsynchronousSocketChannel.suspendRead(
    buffer: ByteBuffer
): Int = suspendCancellableCoroutine { cont ->

    this.read(buffer, null, object : CompletionHandler<Int, Void?> {
        override fun completed(result: Int, attachment: Void?) {
            cont.resume(result)
        }
        override fun failed(exc: Throwable, attachment: Void?) {
            cont.resumeWithException(exc)
        }
    })

    cont.invokeOnCancellation {
        try {
            this.close()
        } catch (_: Exception) {
        }
    }
}
