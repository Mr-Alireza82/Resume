package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun receipt(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "Receipt",
        defaultWidth = 800.dp,
        defaultHeight = 800.dp,
        viewportWidth = 800f,
        viewportHeight = 800f
    ).apply {
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 35f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(759.9f, 173.5f)
                verticalLineToRelative(87.1f)
                curveToRelative(0f, 56.9f, -36f, 92.9f, -92.9f, 92.9f)
                horizontalLineTo(544f)
                verticalLineTo(101.9f)
                curveToRelative(0f, -40f, 32.8f, -72.3f, 72.7f, -72.3f)
                curveToRelative(39.2f, 0.4f, 75.2f, 16.2f, 101.1f, 42.1f)
                curveTo(743.8f, 98f, 759.9f, 134f, 759.9f, 173.5f)
                close()
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 35f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(40.1f, 209.5f)
                verticalLineToRelative(503.9f)
                curveToRelative(0f, 29.9f, 33.8f, 46.8f, 57.6f, 28.8f)
                lineToRelative(61.6f, -46.1f)
                curveToRelative(14.4f, -10.8f, 34.6f, -9.4f, 47.5f, 3.6f)
                lineToRelative(59.8f, 60.1f)
                curveToRelative(14f, 14f, 37.1f, 14f, 51.1f, 0f)
                lineToRelative(60.5f, -60.5f)
                curveToRelative(12.6f, -12.6f, 32.8f, -14f, 46.8f, -3.2f)
                lineToRelative(61.6f, 46.1f)
                curveToRelative(23.8f, 17.6f, 57.6f, 0.7f, 57.6f, -28.8f)
                verticalLineTo(101.6f)
                curveToRelative(0f, -39.6f, 32.4f, -72f, 72f, -72f)
                horizontalLineTo(220f)
                horizontalLineToRelative(-36f)
                curveToRelative(-108f, 0f, -144f, 64.4f, -144f, 144f)
                verticalLineTo(209.5f)
                close()
            }
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 35f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(184f, 281.5f)
                horizontalLineToRelative(216f)
            }
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 35f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(211f, 425.5f)
                horizontalLineToRelative(162f)
            }
    }.build()
}
