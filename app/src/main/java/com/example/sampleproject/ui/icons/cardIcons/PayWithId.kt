package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun payWithId(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "PayWithId",
        defaultWidth = 800.dp,
        defaultHeight = 800.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).apply {
            path(
                stroke = SolidColor(primary),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Bevel,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(14.86f, 2f)
                horizontalLineTo(6f)
                arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 4f, 4f)
                verticalLineTo(20f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
                horizontalLineTo(18f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, -2f)
                verticalLineTo(8.92f)
                arcToRelative(0.94f, 0.94f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.18f, -0.57f)
                lineTo(15.67f, 2.43f)
                arcTo(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 14.86f, 2f)
                close()
            }
            path(
                stroke = SolidColor(secondary),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(8f, 13.4f)
                lineToRelative(2.25f, 2.25f)
                lineToRelative(5.75f, -5.75f)
            }
    }.build()
}
