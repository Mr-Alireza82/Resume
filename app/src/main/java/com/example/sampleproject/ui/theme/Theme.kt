package com.example.sampleproject.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkBlue,
    secondary = DarkRed,
    tertiary = Pink80,
    onBackground = White,
    onSurface = White,
    background = DarkGray,
    onTertiary = Gray,
)

private val LightColorScheme = lightColorScheme(
    primary = LightBlue,
    secondary = LightRed,
    tertiary = Pink40,
    onBackground = DarkGray,
    onSurface = DarkGray,
    background = White,
    onTertiary = Gray,

    /*onPrimary = Color.White,
    onSecondary = Color.White,
    surface = Color.White,*/
)

@Composable
fun SampleProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val typography = getAppTypography() // ✅ dynamically based on locale

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}
