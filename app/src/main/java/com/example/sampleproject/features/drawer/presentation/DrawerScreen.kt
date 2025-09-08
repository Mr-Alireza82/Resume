package com.example.sampleproject.features.drawer.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sampleproject.R
import com.example.sampleproject.features.main.presentation.MainScreenContent
import com.example.sampleproject.features.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerScreen(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    LaunchedEffect(drawerState.isOpen) {
        if (drawerState.isOpen) {
            focusManager.clearFocus()
        }
    }

    BackHandler(enabled = drawerState.isOpen) {
        scope.launch { drawerState.close() }
    }

    // Runs right after it navigated
    LaunchedEffect(navController.currentBackStackEntry) {
        drawerState.snapTo(DrawerValue.Closed)
    }

    fun handleDrawerEvent(event: DrawerEvent) {
        scope.launch {
            when (event) {
                is DrawerEvent.NavigateToAcquire -> {
                    navController.navigate(Screen.Acquire.route)
                }
                is DrawerEvent.NavigateToSupport -> {
                    navController.navigate(Screen.Support.route)
                }
                is DrawerEvent.NavigateToInfo -> {}
            }
        }
    }

    val drawerWidth = LocalWindowInfo.current.containerSize.width.let { px ->
        with(LocalDensity.current) {
            (px * 0.75f).toDp()
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(drawerWidth)
                    .fillMaxHeight()
                    .navigationBarsPadding(),
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(64.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Text(text = stringResource(id = R.string.profile_name),
                                color = Color.White, fontSize = 20.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    NavigationDrawerItem(
                        label = {
                            Text(text = stringResource(id = R.string.acquire),
                                color = MaterialTheme.colorScheme.onSurface)
                                },
                        icon = { Icon(Icons.Default.Person, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface) },
                        selected = false,
                        onClick = { handleDrawerEvent(DrawerEvent.NavigateToAcquire) }
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(text = stringResource(id = R.string.support),
                                color = MaterialTheme.colorScheme.onSurface)
                                },
                        icon = { Icon(Icons.Default.Build, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface) },
                        selected = false,
                        onClick = { handleDrawerEvent(DrawerEvent.NavigateToSupport) }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

                    NavigationDrawerItem(
                        label = {
                            Text(text = stringResource(id = R.string.info),
                                color = MaterialTheme.colorScheme.onSurface)
                                },
                        icon = { Icon(Icons.Default.Info, contentDescription = null, tint = MaterialTheme.colorScheme.onSurface) },
                        selected = false,
                        onClick = { handleDrawerEvent(DrawerEvent.NavigateToInfo) }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = stringResource(id = R.string.version) + " 1.0.0",
                        modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp),
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    ) {
        MainScreenContent(
            drawerState = drawerState,
            onMenuClick = {
                scope.launch {
                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                }
            }
        )
    }
}
