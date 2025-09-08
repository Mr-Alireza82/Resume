package com.example.sampleproject.core.iso.iso8583.message.domain.entity

class IsoMessagePacker {
    private var tpdu: ByteArray? = null
    private var mti: ByteArray? = null
    private var bitmap: ByteArray? = null
    private val dataElement: MutableMap<Int, ByteArray> = mutableMapOf()
    private var body: ByteArray? = null
    private var message: ByteArray? = null

    fun setTPDU(tpdu: ByteArray) = apply { this.tpdu = tpdu }
    fun setMTI(mti: ByteArray) = apply { this.mti = mti }
    fun setBitmap(bitmap: ByteArray) = apply { this.bitmap = bitmap }
    fun setDataElement(id: Int, value: ByteArray) = apply { dataElement[id] = value }
    fun setBody(body: Map<Int, ByteArray>) = apply { dataElement.putAll(body) }
    fun setMessage(message: ByteArray) = apply { this.message = message }

    fun build(): IsoMessage {
        requireNotNull(message) { "Message must be set before building" }

        return IsoMessage(
            tpdu = tpdu ?: ByteArray(0),
            mti = mti ?: ByteArray(0),
            bitmap = bitmap ?: ByteArray(0),
            dataElements = dataElement.toMap(),
            body = body ?: ByteArray(0),
            message = message!!
        )
    }
}
