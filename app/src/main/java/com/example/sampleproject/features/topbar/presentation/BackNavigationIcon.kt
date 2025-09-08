package com.example.sampleproject.features.topbar.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun BackNavigationIcon(
    navController: NavController
) {
    IconButton(onClick = { navController.popBackStack() }) {
        val icon = Icons.AutoMirrored.Filled.ArrowBack
        Icon(
            imageVector = icon,
            contentDescription = "Back",
            tint = Color.White
        )
    }
}
