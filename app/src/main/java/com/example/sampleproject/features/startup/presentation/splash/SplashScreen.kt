package com.example.sampleproject.features.startup.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleproject.features.navigation.Screen
import com.example.sampleproject.features.startup.presentation.splash.contract.SplashContract.Event
import com.example.sampleproject.features.startup.presentation.splash.contract.SplashContract.Intent
import com.example.sampleproject.ui.theme.SampleProjectTheme
import io.github.composegears.valkyrie.fanAvaCardSquare
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val scale = remember { Animatable(1f) }
    var isNavigated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.processIntent(Intent.StartInitialization)
    }

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            if (event is Event.NavigateToMain) {
                navController.navigate(Screen.Main.route) {
                    popUpTo(Screen.Splash.route) { inclusive = true }
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        FanAvaIcon(
            modifier = Modifier
                .size(220.dp)
                .scale(scale.value)
        )
    }
}

@Composable
fun FanAvaIcon(modifier: Modifier = Modifier) {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary

    val icon = remember(primary, secondary) {
        fanAvaCardSquare(primary = primary, secondary = secondary)
    }

    Icon(
        imageVector = icon,
        contentDescription = "FanAva Icon",
        tint = Color.Unspecified,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun LightPreview() {
    SampleProjectTheme(darkTheme = false) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            FanAvaIcon(
                modifier = Modifier.size(220.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    SampleProjectTheme(darkTheme = true) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            FanAvaIcon(
                modifier = Modifier.size(220.dp)
            )
        }
    }
}
