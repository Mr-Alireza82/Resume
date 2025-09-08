package com.example.sampleproject.core.network.tcp.extension

import kotlinx.coroutines.suspendCancellableCoroutine
import java.net.InetSocketAddress
import java.nio.channels.AsynchronousSocketChannel
import java.nio.channels.CompletionHandler
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

suspend fun AsynchronousSocketChannel.suspendConnect(
    address: InetSocketAddress
): Unit = suspendCancellableCoroutine { cont ->

    this.connect(address, null, object : CompletionHandler<Void?, Void?> {
        override fun completed(result: Void?, attachment: Void?) {
            if (cont.isActive) {
                cont.resume(Unit)
            }
        }

        override fun failed(exc: Throwable, attachment: Void?) {
            if (cont.isActive) {
                cont.resumeWithException(exc)
            }
        }
    })

    cont.invokeOnCancellation {
        try {
            this.close()
        } catch (_: Exception) {
        }
    }
}
