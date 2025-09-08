package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun other(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "Other",
        defaultWidth = 100.dp,
        defaultHeight = 100.dp,
        viewportWidth = 100f,
        viewportHeight = 100f
    ).apply {
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 5f
            ) {
                moveTo(50f, 50f)
                moveToRelative(-46.5f, 0f)
                arcToRelative(46.5f, 46.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, 93f, 0f)
                arcToRelative(46.5f, 46.5f, 0f, isMoreThanHalf = true, isPositiveArc = true, -93f, 0f)
            }
            path(fill = SolidColor(primary)) {
                moveTo(26f, 50f)
                moveToRelative(-8f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, 16f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, -16f, 0f)
            }
            path(fill = SolidColor(primary)) {
                moveTo(50f, 50f)
                moveToRelative(-8f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, 16f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, -16f, 0f)
            }
            path(fill = SolidColor(primary)) {
                moveTo(74f, 50f)
                moveToRelative(-8f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, 16f, 0f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = true, isPositiveArc = true, -16f, 0f)
            }
    }.build()
}
