package com.example.sampleproject.core.iso.iso7816.part4.applets.tkti

object VerifyPinApdu {
    fun build(pin: String): ByteArray {
        require(pin.length == 4) { "PIN must be exactly 4 digits" }

        val cpin = pin.toInt()
        val d0 = (cpin / 1000).toByte()
        val d1 = ((cpin % 1000) / 100).toByte()
        val d2 = ((cpin % 100) / 10).toByte()
        val d3 = (cpin % 10).toByte()

        return byteArrayOf(
            0x80.toByte(),  // CLA
            0xA8.toByte(),  // INS
            0x00.toByte(),  // P1
            0x00.toByte(),  // P2
            0x04.toByte(),  // Lc
            d0, d1, d2, d3,
            0x00.toByte()
        )
    }
}
