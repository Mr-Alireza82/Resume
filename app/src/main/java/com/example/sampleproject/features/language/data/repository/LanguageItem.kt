package com.example.sampleproject.features.language.data.repository

import androidx.compose.ui.graphics.vector.ImageVector

data class LanguageItem(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val icon: ImageVector
)
