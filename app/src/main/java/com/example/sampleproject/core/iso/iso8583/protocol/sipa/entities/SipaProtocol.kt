package com.example.sampleproject.core.iso.iso8583.protocol.sipa.entities

import com.example.sampleproject.core.utils.converter.byteArrayToHex

class SipaProtocol(
    val tpdu: ByteArray,
    val mti: ByteArray,
    val bitmap: ByteArray,
    val dataElements: Map<Int, ByteArray>,
    val body: ByteArray,
    val finalMessage: ByteArray
) {

    fun getLength(): ByteArray = finalMessage.copyOfRange(0, 2)

    fun getDataElement(field: Int): ByteArray = dataElements[field] ?: ByteArray(0)

    fun getMessage(): ByteArray = finalMessage

    override fun toString(): String {
        val sb = StringBuilder()
        sb.appendLine("---- SipaProtocol ----")
        sb.appendLine("Length      : ${finalMessage.copyOfRange(0, 2).byteArrayToHex()}")
        sb.appendLine("TPDU        : ${tpdu.byteArrayToHex()}" +
                "(id=${tpdu.copyOfRange(0,1).byteArrayToHex()}, " +
                "destNii=${tpdu.copyOfRange(1,3).byteArrayToHex()}, " +
                "srcNii=${tpdu.copyOfRange(3,5).byteArrayToHex()})")
        sb.appendLine("MTI         : ${mti.byteArrayToHex()}")
        sb.appendLine("Bitmap      : ${bitmap.byteArrayToHex()}")

        if (dataElements.isEmpty()) {
            sb.appendLine("Fields      : (none)")
        } else {
            sb.appendLine("Fields:")
            dataElements.toSortedMap().forEach { (field, value) ->
                sb.appendLine("    F${field.toString().padStart(3, '0')}    : ${value.byteArrayToHex()}")
            }
        }

        sb.appendLine("Full Msg   : ${finalMessage.byteArrayToHex()}")
        sb.appendLine("-----------------------")
        return sb.toString()
    }
}
