package com.example.sampleproject.core.utils.converter

fun ByteArray.byteArrayToHex(): String =
    joinToString("") { "%02X".format(it) }
