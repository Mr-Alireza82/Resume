package com.example.sampleproject.core.iso.iso7816.part4.command.domain

data class ApduCommand(
    val cla: Byte,
    val ins: Byte,
    val p1: Int,
    val p2: Int,
    val lc: Int? = null,
    val data: ByteArray? = null,
    val le: Int? = null
) {
    fun toByteArray(): ByteArray {
        val header = byteArrayOf(cla, ins, p1.toByte(), p2.toByte())

        val body = buildList<Byte> {
            // Lc handling
            val finalLc = lc ?: data?.size
            if (finalLc != null) add(finalLc.toByte())

            // Data handling
            if (data != null) addAll(data.toList())

            // Le handling
            if (le != null) add(le.toByte())
        }.toByteArray()

        return header + body
    }
}
