package com.example.sampleproject.features.topbar.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DrawerNavigationIcon(
    drawerState: DrawerState,
    onMenuClick: () -> Unit
) {
    IconButton(onClick = onMenuClick) {
        val icon = if (drawerState.isClosed) {
            Icons.Filled.Menu
        } else {
            Icons.AutoMirrored.Filled.ArrowBack
        }
        Icon(
            imageVector = icon,
            contentDescription = "Toggle Drawer",
            tint = Color.White
        )
    }
}
