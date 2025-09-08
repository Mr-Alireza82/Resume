package com.example.sampleproject.ui.icons.countries.flag

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun italy(): ImageVector {
    return ImageVector.Builder(
        name = "Italy",
        defaultWidth = 1500.dp,
        defaultHeight = 1000.dp,
        viewportWidth = 3f,
        viewportHeight = 2f
    ).apply {
        path(fill = SolidColor(Color(0xFF009246))) {
            moveTo(0f, 0f)
            horizontalLineToRelative(1f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(-1f)
            close()
        }
        path(fill = SolidColor(Color(0xFFFFFFFF))) {
            moveTo(1f, 0f)
            horizontalLineToRelative(1f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(-1f)
            close()
        }
        path(fill = SolidColor(Color(0xFFCE2B37))) {
            moveTo(2f, 0f)
            horizontalLineToRelative(1f)
            verticalLineToRelative(2f)
            horizontalLineToRelative(-1f)
            close()
        }
    }.build()
}
