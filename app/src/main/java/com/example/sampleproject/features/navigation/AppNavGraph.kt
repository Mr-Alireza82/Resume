package com.example.sampleproject.features.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sampleproject.features.acquire.otherSettings.presentation.OtherSettingsScreen
import com.example.sampleproject.features.acquire.presentation.AcquireScreen
import com.example.sampleproject.features.connection.presentation.component.ConnectionScreen
import com.example.sampleproject.features.language.presentation.LanguageViewModel
import com.example.sampleproject.features.language.presentation.contract.LanguageEffect
import com.example.sampleproject.features.main.presentation.MainScreen
import com.example.sampleproject.features.startup.presentation.splash.SplashScreen
import com.example.sampleproject.features.support.presentation.component.SupportScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppNavGraph(navController: NavHostController) {
    val languageViewModel: LanguageViewModel = hiltViewModel()
    var languageKey by rememberSaveable { mutableStateOf("en") }

    // Collect current language to trigger recomposition
    LaunchedEffect(Unit) {
        languageViewModel.effect.collectLatest {
            if (it is LanguageEffect.LanguageChanged) {
                languageKey = it.language.code
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(Screen.Splash.route) {
            key(languageKey) {
                SplashScreen(navController)
            }
        }

        composable(Screen.Main.route) {
            key(languageKey) {
                MainScreen(navController)
            }
        }

        composable(Screen.Acquire.route) {
            key(languageKey) {
                AcquireScreen(navController)
            }
        }

        composable(Screen.OtherSettings.route) {
            key(languageKey) {
                OtherSettingsScreen(navController)
            }
        }

        composable(Screen.Support.route) {
            key(languageKey) {
                SupportScreen(navController)
            }
        }

        composable(Screen.Connection.route) {
            key(languageKey) {
                ConnectionScreen(navController)
            }
        }
    }
}
