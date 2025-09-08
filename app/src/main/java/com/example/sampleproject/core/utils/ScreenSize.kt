package com.example.sampleproject.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

object ScreenSize {
    @Composable
    fun screenWidthDp(): Dp {
        val px = LocalWindowInfo.current.containerSize.width
        return with(LocalDensity.current) { px.toDp() }
    }

    @Composable
    fun screenHeightDp(): Dp {
        val px = LocalWindowInfo.current.containerSize.height
        return with(LocalDensity.current) { px.toDp() }
    }
}
