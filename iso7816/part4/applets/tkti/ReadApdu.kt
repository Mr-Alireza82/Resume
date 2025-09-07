package com.example.sampleproject.core.iso.iso7816.part4.applets.tkti

object ReadApdu {
    fun build(): ByteArray {

        return byteArrayOf(
            0x00.toByte(),  // CLA
            0x20.toByte(),  // INS
            0x00.toByte(),  // P1
            0x81.toByte(),  // P2
            0x04.toByte(),  // Lc
        )
    }
}
