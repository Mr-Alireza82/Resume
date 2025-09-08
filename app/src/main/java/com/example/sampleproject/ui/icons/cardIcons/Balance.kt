package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun balance(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "Balance",
        defaultWidth = 122.9.dp,
        defaultHeight = 101.3.dp,
        viewportWidth = 122.9f,
        viewportHeight = 101.3f
    ).apply {
            path(fill = SolidColor(primary)) {
                moveTo(120.1f, 36.1f)
                curveToRelative(-1.4f, -1.4f, -3.3f, -2.4f, -5.3f, -2.7f)
                horizontalLineToRelative(0f)
                verticalLineToRelative(-16.1f)
                curveToRelative(0f, -4.8f, -2f, -9.1f, -5.1f, -12.2f)
                reflectiveCurveTo(102.2f, 0f, 97.4f, 0f)
                horizontalLineTo(17.3f)
                curveTo(12.6f, 0f, 8.2f, 1.9f, 5.1f, 5.1f)
                curveTo(2f, 8.2f, 0f, 12.6f, 0f, 17.3f)
                verticalLineToRelative(66.6f)
                curveToRelative(0f, 4.8f, 2f, 9.1f, 5.1f, 12.2f)
                curveToRelative(3.1f, 3.1f, 7.5f, 5.1f, 12.2f, 5.1f)
                horizontalLineToRelative(80f)
                curveToRelative(4.8f, 0f, 9.1f, -2f, 12.2f, -5.1f)
                curveToRelative(3.1f, -3.1f, 5.1f, -7.5f, 5.1f, -12.2f)
                verticalLineToRelative(-9.6f)
                curveToRelative(2f, -0.4f, 3.8f, -1.4f, 5.2f, -2.8f)
                curveToRelative(1.8f, -1.8f, 2.9f, -4.3f, 2.9f, -7.1f)
                verticalLineToRelative(-21.6f)
                curveToRelative(0f, -2.6f, -1.1f, -5f, -2.8f, -6.8f)
                close()
                moveTo(109f, 18.6f)
                curveToRelative(-0.1f, -0.1f, -0.2f, -0.2f, -0.4f, -0.3f)
                curveToRelative(0.1f, 0.8f, 0f, 1.7f, -0.2f, 2.4f)
                curveToRelative(0.2f, 0.5f, 0.3f, 1.1f, 0.3f, 1.6f)
                horizontalLineToRelative(0f)
                curveToRelative(0f, 0.6f, 0f, 1.1f, -0.2f, 1.5f)
                curveToRelative(0.2f, 0.7f, 0.1f, 1.5f, 0f, 2.2f)
                curveToRelative(0.3f, 1f, 0.4f, 2.1f, 0.1f, 3.1f)
                curveToRelative(0.1f, 0.4f, 0.3f, 0.8f, 0.4f, 1.3f)
                verticalLineToRelative(2.8f)
                horizontalLineToRelative(-18.4f)
                curveToRelative(-5.6f, 0f, -10.6f, 2.3f, -14.3f, 5.9f)
                curveToRelative(-3.6f, 3.6f, -5.9f, 8.7f, -5.9f, 14.2f)
                verticalLineToRelative(0.9f)
                curveToRelative(0f, 5.5f, 2.3f, 10.6f, 5.9f, 14.2f)
                curveToRelative(3.7f, 3.6f, 8.7f, 5.9f, 14.2f, 5.9f)
                horizontalLineToRelative(18.4f)
                verticalLineToRelative(9.5f)
                curveToRelative(0f, 3.2f, -1.3f, 6.1f, -3.4f, 8.2f)
                reflectiveCurveToRelative(-5f, 3.4f, -8.2f, 3.4f)
                horizontalLineTo(17.3f)
                curveToRelative(-3.2f, 0f, -6.1f, -1.3f, -8.2f, -3.4f)
                curveToRelative(-2.1f, -2.1f, -3.4f, -5f, -3.4f, -8.2f)
                verticalLineTo(17.3f)
                curveToRelative(0f, -3.2f, 1.3f, -6.1f, 3.4f, -8.2f)
                curveToRelative(2.1f, -2.1f, 5f, -3.4f, 8.2f, -3.4f)
                horizontalLineToRelative(80f)
                curveToRelative(3.2f, 0f, 6.1f, 1.3f, 8.2f, 3.4f)
                curveToRelative(2.1f, 2.1f, 3.4f, 5f, 3.4f, 8.2f)
                verticalLineToRelative(1.3f)
                close()
                moveTo(95.5f, 53.4f)
                curveToRelative(0f, 4f, -3.3f, 7.3f, -7.3f, 7.3f)
                reflectiveCurveToRelative(-7.3f, -3.3f, -7.3f, -7.3f)
                reflectiveCurveToRelative(3.3f, -7.3f, 7.3f, -7.3f)
                reflectiveCurveToRelative(7.3f, 3.3f, 7.3f, 7.3f)
                close()
            }
            path(fill = SolidColor(secondary)) {
                moveTo(89.8f, 20f)
                curveToRelative(9f, 0f, 16.3f, -0.2f, 19.2f, 10.5f)
                verticalLineToRelative(-11.8f)
                curveToRelative(-4.8f, -4.5f, -11.6f, -4.4f, -19.3f, -4.4f)
                curveToRelative(-0.4f, 0f, -0.8f, 0f, -2.9f, 0f)
                horizontalLineTo(18.1f)
                curveToRelative(-1.6f, 0f, -2.9f, 1.3f, -2.9f, 2.9f)
                reflectiveCurveToRelative(1.3f, 2.9f, 2.9f, 2.9f)
                horizontalLineToRelative(68.8f)
                curveToRelative(0f, 0f, 1.4f, 0f, 2.9f, 0f)
                close()
            }
    }.build()
}
