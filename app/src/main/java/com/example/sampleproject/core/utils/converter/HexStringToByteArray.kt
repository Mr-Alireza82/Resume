package com.example.sampleproject.core.utils.converter

fun String.hexToByteArray(): ByteArray {
    check(length % 2 == 0) { "Hex string must have an even length" }
    return chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
}
