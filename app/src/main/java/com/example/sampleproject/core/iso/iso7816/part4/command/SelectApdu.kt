package com.example.sampleproject.core.iso.iso7816.part4.command

object SelectApdu {
    fun build(aidHex: String): ByteArray {
        val aid = aidHex.chunked(2).map { it.toInt(16).toByte() }.toByteArray()
        val lc = aid.size.toByte()

        return byteArrayOf(
            0x00,       // CLA
            0xA4.toByte(), // INS
            0x04,       // P1 (select by AID)
            0x00,       // P2
            lc          // Lc = length of AID
        ) + aid
    }
}
