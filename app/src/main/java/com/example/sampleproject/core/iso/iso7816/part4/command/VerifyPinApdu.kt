package com.example.sampleproject.core.iso.iso7816.part4.command

object VerifyPinApdu {
    fun build(pin: String): ByteArray {
        require(pin.length == 4) { "PIN must be exactly 4 digits" }

        val pinBytes = ByteArray(4)
        for (i in 0 until 4) {
            pinBytes[i] = (pin[i].code - '0'.code).toByte()
        }

        return byteArrayOf(
            0x80.toByte(), // CLA
            0xA8.toByte(), // INS
            0x00.toByte(), // P1
            0x00.toByte(), // P2
            0x04.toByte()  // Lc = 4 bytes of PIN
        ) + pinBytes + byteArrayOf(0x00) // Le
    }
}