package com.example.sampleproject.core.iso.iso8583.protocol.sipa.builders

import android.util.Log
import com.example.sampleproject.core.di.ApplicationHolder
import com.example.sampleproject.core.iso.iso8583.message.domain.entity.Bitmap
import com.example.sampleproject.core.iso.iso8583.message.domain.model.DefaultFieldMapping
import com.example.sampleproject.core.iso.iso8583.message.domain.model.MtiType
import com.example.sampleproject.core.iso.iso8583.message.domain.model.ProcessCodeType
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.components.body.fields.de12_time.Time
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.components.body.fields.de13_date.Date
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.components.body.fields.de48_additionalData.AdditionalData
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.components.header.tpdu.Tpdu
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.domain.entity.fields.stan.domain.entity.Stan
import com.example.sampleproject.core.iso.iso8583.protocol.sipa.entities.SipaProtocol
import com.example.sampleproject.core.utils.converter.byteArrayToHex
import com.example.sampleproject.core.utils.converter.hexToByteArray

class SipaProtocolBuilder private constructor(
    private val mti: ByteArray,
    private val processCode: ByteArray
) {
    private val context = ApplicationHolder.applicationContext

    val mtiType: MtiType? = MtiType.fromValue(mti)
    val processCodeType: ProcessCodeType? = ProcessCodeType.fromValue(processCode)

    init {
        require(mti.size == 2) { "MTI must be 2 bytes" }
        require(processCode.size == 3) { "Process Code must be 3 bytes (6 hex digits)" }
    }

    suspend fun build(): SipaProtocol {
        val tpdu = Tpdu().build().getTpdu()

        val activeFields = DefaultFieldMapping.getFieldsFor(mti, processCode)
        val bitmap = Bitmap.fromFields(activeFields).toByteArray()

        val dataElements = activeFields.associateWith { buildDefaultValueForField(it) }
        val body = buildBody(dataElements)

        val rawMessage = tpdu + mti + bitmap + body

        val halfSize = rawMessage.size
        val hexSize = halfSize.toString(16).uppercase()
        val paddedHexSize = hexSize.padStart(4, '0')
        val length = paddedHexSize.hexToByteArray()

        val finalMessage = length + rawMessage

        Log.i("ISO8583", "Body: ${body.byteArrayToHex()}")
        Log.i("ISO8583", "Final: ${finalMessage.byteArrayToHex()}")

        val protocol = SipaProtocol(
            tpdu = tpdu,
            mti = mti,
            bitmap = bitmap,
            dataElements = dataElements,
            body = body,
            finalMessage = finalMessage
        )

        Log.i("SipaProtocolXXXX", protocol.toString())  // 👈 log full dump here

        return protocol
    }

    private fun buildBody(fields: Map<Int, ByteArray>): ByteArray =
        fields.toSortedMap().values.fold(ByteArray(0)) { acc, bytes -> acc + bytes }

    private suspend fun buildDefaultValueForField(field: Int): ByteArray =
        when (field) {
            1, 2, -> ByteArray(0) // Not used
            3 -> processCode
            11 -> Stan(context).getStan().hexToByteArray()
            12 -> Time.getCurrentTime().hexToByteArray()
            13 -> Date.getCurrentDate().hexToByteArray()
            24 -> Tpdu().build().getDestinationNii()
            48 -> AdditionalData.getAdditionalData(mti, processCode)
            else -> error("No default implementation defined for ISO8583 field $field")
        }

    override fun toString(): String =
        "SipaProtocolBuilder(mti='${mti.byteArrayToHex()}', " +
                "processCode='${processCode.byteArrayToHex()}', " +
                "mtiType=$mtiType, processCodeType=$processCodeType)"

    companion object {
        fun from(mti: Any, processCode: Any): SipaProtocolBuilder =
            SipaProtocolBuilder(coerceHexToBytes(mti), coerceHexToBytes(processCode))

        private fun coerceHexToBytes(input: Any): ByteArray =
            when (input) {
                is ByteArray -> input
                is Int -> input.toString().hexToByteArray()
                is String -> input.hexToByteArray()
                is MtiType -> input.hexValue
                is ProcessCodeType -> input.hexValue
                else -> error("Unsupported type for field: ${input::class.simpleName}")
            }
    }
}
