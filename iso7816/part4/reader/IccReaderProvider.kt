package com.example.sampleproject.core.iso.iso7816.part4.reader

import android.content.Context
import com.pos.sdk.cardreader.POICardManager
import com.pos.sdk.cardreader.PosIccCardReader

object IccReaderProvider {
    fun getReader(context: Context): PosIccCardReader {
        return POICardManager.getDefault(context).getIccCardReader()
    }
}
