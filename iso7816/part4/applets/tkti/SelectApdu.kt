package com.example.sampleproject.core.iso.iso7816.part4.applets.tkti

import com.example.sampleproject.core.iso.iso7816.part4.command.domain.ApduCommandBuilder

object SelectApdu {

    fun build(): ByteArray {

        return ApduCommandBuilder
            .setCla(0x00)
            .setIns(0xA4.toByte())
            .setP1(0x04)
            .setP2(0x00)
            .setLc(0x07)
            .setData(
                byteArrayOf(
                    0xA0.toByte(),
                    0x00,
                    0x00,
                    0x02,
                    0x02,
                    0x90.toByte(),
                    0x02
                )
            )
            .setLe(null)
            .build()
    }
}
