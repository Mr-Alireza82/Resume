package com.example.sampleproject.core.iso.iso7816.part4.model

import com.example.sampleproject.core.utils.converter.byteArrayToHex

data class ApduResponse(
    val data: ByteArray?,
    val sw1: Int,
    val sw2: Int
) {

    fun isSuccess(): Boolean = (sw1 and 0xFF) == 0x90 && (sw2 and 0xFF) == 0x00

    override fun toString(): String {
        val swHex = "%04X".format((sw1 and 0xFF shl 8) or (sw2 and 0xFF))
        val dataHex = data?.byteArrayToHex()
        return "SW=$swHex, RSP=$dataHex"
    }
}
