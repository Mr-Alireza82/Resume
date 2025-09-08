package com.example.sampleproject.features.main.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sampleproject.features.drawer.presentation.DrawerScreen

@Composable
fun MainScreen(navController: NavController) {
    DrawerScreen(navController)
}
