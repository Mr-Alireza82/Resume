package com.example.sampleproject.features.main.presentation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import com.example.sampleproject.R
import com.example.sampleproject.features.language.presentation.LanguageDropdownMenu
import com.example.sampleproject.features.topbar.presentation.DrawerNavigationIcon
import com.example.sampleproject.features.topbar.presentation.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    drawerState: DrawerState,
    onMenuClick: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus() // ✅ clear focus anywhere
            }
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    title = stringResource(id = R.string.title_home),
                    scrollBehavior = scrollBehavior,
                    navigationIcon = {
                        DrawerNavigationIcon(
                            drawerState = drawerState,
                            onMenuClick = onMenuClick
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
            MainBodyContent(innerPadding)
        }
    }
}
