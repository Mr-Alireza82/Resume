package com.example.sampleproject.core.iso.iso7816.part4.command

object ReadApdu {
    fun build(offset: Int = 0, length: Int = 0x20): ByteArray {
        val p1 = (offset shr 8).toByte()
        val p2 = (offset and 0xFF).toByte()
        return byteArrayOf(
            0x80.toByte(),          // CLA
            0xB5.toByte(),          // INS = READ BINARY
            0x00,
            0x01,
            0x16
        )
    }
}
