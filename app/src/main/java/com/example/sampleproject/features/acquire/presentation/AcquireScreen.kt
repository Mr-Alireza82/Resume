package com.example.sampleproject.features.acquire.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sampleproject.R
import com.example.sampleproject.features.acquire.presentation.contract.AcquireEvent
import com.example.sampleproject.features.language.presentation.LanguageDropdownMenu
import com.example.sampleproject.features.navigation.Screen
import com.example.sampleproject.features.topbar.presentation.BackNavigationIcon
import com.example.sampleproject.features.topbar.presentation.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcquireScreen(navController: NavController) {

    val viewModel: AcquireViewModel = viewModel()

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.title_acquire),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    BackNavigationIcon(
                        navController = navController
                    )
                },
                actions = {
                    LanguageDropdownMenu()
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { innerPadding ->
        AcquireScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp),
            onItemClick = { event ->
                when (event) {
                    is AcquireEvent.FontStyle -> {}
                    is AcquireEvent.OtherSettings -> navController.navigate(Screen.OtherSettings.route)
                }
            }
        )
    }
}

@Composable
fun AcquireScreenContent(
    modifier: Modifier = Modifier,
    onItemClick: (AcquireEvent) -> Unit
) {
    val actions = listOf(
        AcquireEvent.FontStyle,
        AcquireEvent.OtherSettings
    )

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(actions) { action ->
            val label = when (action) {
                AcquireEvent.FontStyle -> stringResource(R.string.acquire_font_style)
                AcquireEvent.OtherSettings -> stringResource(R.string.acquire_other_settings)
            }

            val icon = when (action) {
                AcquireEvent.FontStyle -> Icons.Default.Create
                AcquireEvent.OtherSettings -> Icons.Default.Settings
            }

            NavigationDrawerItem(
                label = { Text(label, color = MaterialTheme.colorScheme.onSurface) },

                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                },

                selected = false,
                onClick = { onItemClick(action) },
                modifier = Modifier.fillMaxSize(),
                shape = RectangleShape
            )
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}
