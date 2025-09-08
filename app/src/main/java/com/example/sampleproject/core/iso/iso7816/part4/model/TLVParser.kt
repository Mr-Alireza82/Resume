package com.example.sampleproject.core.iso.iso7816.part4.model

class TLVParser {

    private fun parseSelectResponse(response: ByteArray?): Map<String, ByteArray> {
        val result = mutableMapOf<String, ByteArray>()
        if (response == null) return result

        var i = 0
        while (i < response.size - 1) {
            val tagStart = response[i]
            val isMultiByteTag = (tagStart.toInt() and 0x1F) == 0x1F
            val tagBuilder = StringBuilder("%02X".format(tagStart))

            if (isMultiByteTag && i + 1 < response.size) {
                tagBuilder.append("%02X".format(response[i + 1]))
                i++
            }

            val tag = tagBuilder.toString()
            val lengthByte = response.getOrNull(++i) ?: break
            val len = lengthByte.toInt() and 0xFF

            if (i + len >= response.size) break
            val value = response.copyOfRange(i + 1, i + 1 + len)
            result["$tag|$len"] = value
            i += len
        }

        return result
    }

    fun getResponseAsString(response: ByteArray?): String {
        val parsed = parseSelectResponse(response)
        return parsed.entries.joinToString(" ") { (tagLen, value) ->
            val tag = tagLen.substringBefore("|")
            val len = tagLen.substringAfter("|").toInt()
            "$tag${"%02X".format(len)}${value.joinToString("") { "%02X".format(it) }}"
        }
    }
}
