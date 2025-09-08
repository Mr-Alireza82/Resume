package com.example.sampleproject.features.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Main : Screen("main")

    data object Acquire : Screen("acquire")
    data object OtherSettings : Screen("OtherSettings")

    data object Support : Screen("support")
    data object Connection : Screen("connection")
}
