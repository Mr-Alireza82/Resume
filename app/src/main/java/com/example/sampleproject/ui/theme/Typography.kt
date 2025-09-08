package com.example.sampleproject.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sampleproject.R
import com.example.sampleproject.features.language.domain.model.Language
import java.util.Locale

private val DefaultFontFamily = FontFamily.Default

private val OldPersianFontFamily = FontFamily(
    Font(R.font.noto_sans_old_persian, FontWeight.Normal)
)

@Composable
fun getAppFontFamily(): FontFamily {
    val langTag = Locale.getDefault().toLanguageTag()
    return if (langTag.equals(Language.HOKHSHIANE.code, ignoreCase = true)) {
        OldPersianFontFamily
    } else {
        DefaultFontFamily
    }
}

@Composable
fun getAppTypography(): androidx.compose.material3.Typography {
    val fontFamily = getAppFontFamily()
    return androidx.compose.material3.Typography(
        bodyLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Normal
        ),
        titleLarge = TextStyle(
            fontFamily = fontFamily,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold
        ),
        // Add more as needed (titleSmall, bodyMedium, etc.)
    )
}
