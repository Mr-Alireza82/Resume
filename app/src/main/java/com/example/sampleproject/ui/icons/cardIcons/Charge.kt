package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun charge(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "Charge",
        defaultWidth = 48.dp,
        defaultHeight = 48.dp,
        viewportWidth = 48f,
        viewportHeight = 48f
    ).apply {
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(13.2f, 1.4f)
                verticalLineToRelative(7.9f)
            }
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19.1f, 1.4f)
                verticalLineToRelative(7.9f)
            }
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(25f, 1.4f)
                verticalLineToRelative(7.9f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(40.7f, 44.7f)
                curveToRelative(0f, 1.1f, -0.9f, 2f, -2f, 2f)
                horizontalLineTo(9.2f)
                curveToRelative(-1.1f, 0f, -2f, -0.9f, -2f, -2f)
                verticalLineTo(3.3f)
                curveToRelative(0f, -1.1f, 0.9f, -2f, 2f, -2f)
                horizontalLineToRelative(20.7f)
                curveToRelative(0.6f, 0f, 1.1f, 0.3f, 1.5f, 0.7f)
                lineToRelative(8.8f, 10.7f)
                curveToRelative(0.3f, 0.4f, 0.4f, 0.8f, 0.4f, 1.2f)
                verticalLineToRelative(30.6f)
                close()
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(17.1f, 23f)
                horizontalLineToRelative(13.8f)
                reflectiveCurveToRelative(3.9f, 0f, 3.9f, 3.9f)
                verticalLineToRelative(9.8f)
                reflectiveCurveToRelative(0f, 3.9f, -3.9f, 3.9f)
                horizontalLineToRelative(-13.8f)
                reflectiveCurveToRelative(-3.9f, 0f, -3.9f, -3.9f)
                verticalLineToRelative(-9.8f)
                reflectiveCurveToRelative(0f, -3.9f, 3.9f, -3.9f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(19.1f, 40.7f)
                verticalLineToRelative(-17.7f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28.9f, 40.7f)
                verticalLineToRelative(-17.7f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28.9f, 28.9f)
                horizontalLineToRelative(5.9f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(13.2f, 28.9f)
                horizontalLineToRelative(5.9f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(28.9f, 34.8f)
                horizontalLineToRelative(5.9f)
            }
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(13.2f, 34.8f)
                horizontalLineToRelative(5.9f)
            }
    }.build()
}
