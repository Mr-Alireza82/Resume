package com.example.sampleproject.ui.icons.cardIcons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

fun purchase(primary: Color, secondary: Color): ImageVector {
    return ImageVector.Builder(
        name = "Purchase",
        defaultWidth = 800.dp,
        defaultHeight = 754.5.dp,
        viewportWidth = 800f,
        viewportHeight = 754.5f
    ).apply {
            path(fill = SolidColor(primary)) {
                moveTo(800f, 150.9f)
                horizontalLineTo(162.4f)
                lineTo(130.1f, 0f)
                horizontalLineTo(0f)
                verticalLineToRelative(39.3f)
                horizontalLineToRelative(98.4f)
                lineToRelative(23.8f, 111.6f)
                lineToRelative(89.5f, 419.5f)
                lineToRelative(473.1f, 0.4f)
                verticalLineToRelative(-39.3f)
                lineToRelative(-441.3f, -0.3f)
                lineToRelative(-20.2f, -94.5f)
                horizontalLineToRelative(462.3f)
                lineToRelative(114.3f, -285.7f)
                close()
                moveTo(742f, 190.2f)
                lineToRelative(-35.3f, 88f)
                horizontalLineTo(189.6f)
                lineToRelative(-18.8f, -88f)
                horizontalLineToRelative(571.2f)
                close()
                moveTo(215f, 397.4f)
                lineToRelative(-22.4f, -104.9f)
                horizontalLineToRelative(508.4f)
                lineToRelative(-42f, 104.9f)
                horizontalLineTo(215f)
                close()
            }
            path(fill = SolidColor(secondary)) {
                moveTo(278.2f, 643.8f)
                curveToRelative(25.6f, 0f, 46.4f, 20.8f, 46.4f, 46.4f)
                reflectiveCurveToRelative(-20.8f, 46.4f, -46.4f, 46.4f)
                reflectiveCurveToRelative(-46.4f, -20.8f, -46.4f, -46.4f)
                reflectiveCurveToRelative(20.8f, -46.4f, 46.4f, -46.4f)
                moveTo(278.2f, 626f)
                curveToRelative(-35.4f, 0f, -64.3f, 28.7f, -64.3f, 64.3f)
                reflectiveCurveToRelative(28.7f, 64.3f, 64.3f, 64.3f)
                reflectiveCurveToRelative(64.3f, -28.7f, 64.3f, -64.3f)
                reflectiveCurveToRelative(-28.8f, -64.3f, -64.3f, -64.3f)
                close()
                moveTo(596.4f, 643.8f)
                curveToRelative(25.6f, 0f, 46.4f, 20.8f, 46.4f, 46.4f)
                reflectiveCurveToRelative(-20.8f, 46.4f, -46.4f, 46.4f)
                reflectiveCurveToRelative(-46.4f, -20.8f, -46.4f, -46.4f)
                reflectiveCurveToRelative(20.8f, -46.4f, 46.4f, -46.4f)
                moveTo(596.4f, 626f)
                curveToRelative(-35.4f, 0f, -64.3f, 28.7f, -64.3f, 64.3f)
                reflectiveCurveToRelative(28.7f, 64.3f, 64.3f, 64.3f)
                reflectiveCurveToRelative(64.3f, -28.7f, 64.3f, -64.3f)
                reflectiveCurveToRelative(-28.8f, -64.3f, -64.3f, -64.3f)
                close()
                moveTo(342.4f, 190.2f)
                horizontalLineToRelative(14.3f)
                verticalLineToRelative(207.2f)
                horizontalLineToRelative(-14.3f)
                verticalLineToRelative(-207.2f)
                close()
                moveTo(525f, 190.2f)
                horizontalLineToRelative(14.3f)
                verticalLineToRelative(207.2f)
                horizontalLineToRelative(-14.3f)
                verticalLineToRelative(-207.2f)
                close()
            }
            path(fill = SolidColor(primary)) {
                moveTo(189.6f, 278.2f)
                lineToRelative(3f, 14.3f)
                lineToRelative(-3f, -14.3f)
                close()
            }
        }.build()

        return _Purchase!!
    }

@Suppress("ObjectPropertyName")
private var _Purchase: ImageVector? = null
