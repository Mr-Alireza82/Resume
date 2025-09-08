package com.example.sampleproject.core.utils.converter

fun String.asciiToHex(): String = this.toByteArray().joinToString("") {
    it.toUByte().toString(16).padStart(2, '0')
}
