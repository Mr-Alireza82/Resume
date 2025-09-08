package com.example.sampleproject.core.utils

import android.view.View
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.text.layoutDirection
import java.util.Locale

fun getLayoutDirection(languageCode: String): LayoutDirection {
    val locale = Locale.forLanguageTag(languageCode)
    val direction = locale.layoutDirection
    return if (direction == View.LAYOUT_DIRECTION_RTL) LayoutDirection.Rtl else LayoutDirection.Ltr
}
