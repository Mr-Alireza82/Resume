package com.example.sampleproject.core.iso.iso7816.part4.session

import android.util.Log
import com.example.sampleproject.core.iso.iso7816.part4.applets.tkti.ReadApdu
import com.example.sampleproject.core.iso.iso7816.part4.applets.tkti.SelectApdu
import com.example.sampleproject.core.iso.iso7816.part4.applets.tkti.VerifyPinApdu
import com.example.sampleproject.core.iso.iso7816.part4.model.ApduResponse
import com.example.sampleproject.core.utils.converter.byteArrayToHex
import com.pos.sdk.cardreader.PosIccCardReader
import com.pos.sdk.utils.PosByteArray
import kotlinx.coroutines.delay

class CardSession(private val reader: PosIccCardReader) {

    suspend fun start(): Boolean {
        if (reader.open() != 0) return false

        val detect = waitForCardWithTimeout()
        val reset = reader.reset()

        return detect && reset == 0
    }

    suspend fun getAtrHex(): String? = reader.getCardReaderInfo()?.mAttribute?.byteArrayToHex()

    suspend fun selectAid(aid: String): ApduResponse {
        val iccReader = reader.getCardReaderInfo()
        Log.i("iccReader", iccReader.toString())

        val apdu = SelectApdu.build(aid)
        val rsp = PosByteArray()
        val sw = PosByteArray()

        val result = reader.transmitApdu(apdu, rsp, sw)

        return ApduResponse(
            rsp.buffer,
            sw.buffer?.getOrNull(0)?.toInt() ?: -1,
            sw.buffer?.getOrNull(1)?.toInt() ?: -1
        )
    }

    suspend fun verifyPin(pin: String): ApduResponse {
        val apdu = VerifyPinApdu.build(pin)
        val rsp = PosByteArray()
        val sw = PosByteArray()

        val result = reader.transmitApdu(apdu, rsp, sw)

        return ApduResponse(
            data = rsp.buffer,
            sw1 = sw.buffer?.getOrNull(0)?.toInt() ?: -1,
            sw2 = sw.buffer?.getOrNull(1)?.toInt() ?: -1
        )
    }

    suspend fun readKeyApdu(): ApduResponse {
        val apdu = ReadApdu.build()
        val rsp = PosByteArray()
        val sw = PosByteArray()

        val result = reader.transmitApdu(apdu, rsp, sw)

        return ApduResponse(
            data = rsp.buffer,
            sw1 = sw.buffer?.getOrNull(0)?.toInt() ?: -1,
            sw2 = sw.buffer?.getOrNull(1)?.toInt() ?: -1
        )
    }

    suspend fun scanWithPinApdu(pin: String): List<ApduResponse> {
        val responses = mutableListOf<ApduResponse>()
        val pinBytes = pin.toByteArray(Charsets.US_ASCII)
        val lc = pinBytes.size.toByte()

        for (ins in 0x00..0xFF) {
            for (p2 in 0x00..0xFF) {
                val apdu = byteArrayOf(
                    0x00.toByte(),       // CLA
                    ins.toByte(),        // INS (guessing command)
                    0x00.toByte(),       // P1
                    p2.toByte(),         // P2 (reference)
                    lc                   // Lc (PIN length)
                ) + pinBytes             // Data (PIN)

                val rsp = PosByteArray()
                val sw = PosByteArray()
                val result = reader.transmitApdu(apdu, rsp, sw)

                val response = ApduResponse(
                    data = rsp.buffer,
                    sw1 = sw.buffer?.getOrNull(0)?.toInt() ?: -1,
                    sw2 = sw.buffer?.getOrNull(1)?.toInt() ?: -1
                )

                Log.i("PIN_SCAN_Checking", "Checking → INS=%02X P2=%02X → $response".format(ins, p2))

                if (response.isSuccess()) {
                    Log.i("PIN_SCAN_SCUCESS", "VALID → INS=%02X P2=%02X → $response".format(ins, p2))
                    responses.add(response)
                }
            }
        }

        return responses
    }

    private suspend fun waitForCardWithTimeout(timeout: Int = 45): Boolean {
        repeat(timeout) {
            if (reader.detect() == 0) return true
            delay(1000)
        }
        return false
    }

    private suspend fun waitForCard(): Boolean {
        while (true) {
            if (reader.detect() == 0) return true
            delay(1000)
        }
        return false
    }
}
