package com.example.sampleproject.features.main.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class CardModel(
    val id: Int,
    val icon: ImageVector,
    val label: String,
    val onClick: (() -> Unit)
)
