package com.example.sampleproject.core.iso.iso8583.message.domain.entity

data class IsoMessage(
    val tpdu: ByteArray,
    val mti: ByteArray,
    val bitmap: ByteArray,
    val dataElements: Map<Int, ByteArray>,
    val body: ByteArray,
    val message: ByteArray
) {

    fun getLength(): ByteArray = message.copyOfRange(0, 2)

    fun getDataElement(field: Int): ByteArray = dataElements[field] ?: ByteArray(0)

    //fun getMessage(): ByteArray = message

/*    fun getLength(): ByteArray = ByteArray(0)

    fun getId(): ByteArray = ByteArray(0)

    fun getDestinationNii(): ByteArray = ByteArray(0)

    fun getOriginNii(): ByteArray = ByteArray(0)

    fun getTpdu(): ByteArray = ByteArray(0)

    fun getMti(): ByteArray = ByteArray(0)

    fun getBitmap(): ByteArray = ByteArray(0)

    fun getHeader(): ByteArray = ByteArray(0)

    fun getDataElement(field: Int): ByteArray = ByteArray(0)

    fun getBody(): ByteArray = ByteArray(0)

    fun getMessage(): ByteArray = ByteArray(0)*/
}
