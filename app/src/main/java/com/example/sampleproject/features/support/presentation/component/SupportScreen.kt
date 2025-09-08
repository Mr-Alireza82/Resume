package com.example.sampleproject.features.support.presentation.component

import android.app.Activity
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sampleproject.R
import com.example.sampleproject.core.network.tcp.presentation.contract.ConnectionState
import com.example.sampleproject.core.network.tcp.presentation.contract.ReadState
import com.example.sampleproject.core.network.tcp.presentation.contract.WriteState
import com.example.sampleproject.features.language.presentation.LanguageDropdownMenu
import com.example.sampleproject.features.navigation.Screen
import com.example.sampleproject.features.support.presentation.contract.SupportEvent
import com.example.sampleproject.features.support.presentation.viewmodel.SupportViewModel
import com.example.sampleproject.features.topbar.presentation.BackNavigationIcon
import com.example.sampleproject.features.topbar.presentation.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SupportScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    val viewModel: SupportViewModel = viewModel()

    val connectionState by viewModel.connectionState.collectAsState()
    val previousConnectionState = remember { mutableStateOf<ConnectionState?>(null) }

    val writeState by viewModel.writeState.collectAsState()
    val previousWriteState = remember { mutableStateOf<WriteState?>(null) }

    val readState by viewModel.readState.collectAsState()
    val previousReadState = remember { mutableStateOf<ReadState?>(null) }

    LaunchedEffect(connectionState) {
        if (connectionState != previousConnectionState.value) {
            previousConnectionState.value = connectionState

            when (val state = connectionState) {
                is ConnectionState.Connecting -> Log.i("TCPClient", "🔌 Connecting...")
                is ConnectionState.Reconnecting -> Log.i("TCPClient", "♻️ Reconnecting... attempt ${state.attempt}")
                is ConnectionState.ConnectSuccess -> Log.i("TCPClient", "✅ Connected successfully.")
                is ConnectionState.ConnectFailed -> Log.i("TCPClient", "❌ Connection failed: ${state.reason}")
                else -> Unit
            }
        }
    }

    LaunchedEffect(writeState) {
        if (writeState != previousWriteState.value) {
            previousWriteState.value = writeState

            when (val state = writeState) {
                is WriteState.Writing -> Log.i("TCPClient", "✍️ Writing to server...")
                is WriteState.ReWriting -> Log.i("TCPClient", "✍️ ReWriting... attempt ${state.attempt}")
                is WriteState.WriteSuccess -> Log.i("TCPClient", "✅ Write completed.")
                is WriteState.WriteFailed -> Log.i("TCPClient", "❌ Write failed: ${state.reason}")
                else -> Unit
            }
        }
    }

    LaunchedEffect(readState) {
        if (readState != previousReadState.value) {
            previousReadState.value = readState

            when (val state = readState) {
                is ReadState.Reading -> Log.i("TCPClient", "📖 Reading from server...")
                is ReadState.ReReading -> Log.i("TCPClient", "🔁 ReReading... attempt ${state.attempt}")
                is ReadState.ReadSuccess -> Log.i("TCPClient", "✅ Read completed.")
                is ReadState.ReadFailed -> Log.i("TCPClient", "❌ Read failed: ${state.reason}")
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.title_support),
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    BackNavigationIcon(navController)
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
        SupportScreenContent(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 16.dp),
            onItemClick = { event ->
                when (event) {
                    is SupportEvent.UploadMasterKey -> {}
                    is SupportEvent.ConnectionSettings -> navController.navigate(Screen.Connection.route)
                    is SupportEvent.GetAuthorizationKeys -> viewModel.startTcpSession()
                    is SupportEvent.GetConfiguration -> {}
                    is SupportEvent.ResetSupportPassword -> {}
                    is SupportEvent.Exit -> activity?.finishAffinity()
                    is SupportEvent.Mac800 -> {}
                }
            }
        )
    }
}

@Composable
fun SupportScreenContent(
    modifier: Modifier = Modifier,
    onItemClick: (SupportEvent) -> Unit
) {
    val actions = listOf(
        SupportEvent.ConnectionSettings,
        SupportEvent.GetAuthorizationKeys,
        SupportEvent.GetConfiguration,
        SupportEvent.UploadMasterKey,
        SupportEvent.ResetSupportPassword,
        SupportEvent.Mac800,
        SupportEvent.Exit
    )

    val mac800Active = remember { mutableStateOf(true) }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(actions) { action ->
            if (action is SupportEvent.Mac800) {
                NavigationDrawerItem(
                    label = { Text("MAC800", color = MaterialTheme.colorScheme.onSurface) },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "MAC800",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    selected = false,
                    onClick = { mac800Active.value = !mac800Active.value },
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape,
                    badge = {
                        Switch(
                            modifier = Modifier.scale(0.7f),
                            checked = mac800Active.value,
                            onCheckedChange = { checked ->
                                mac800Active.value = checked
                                onItemClick(SupportEvent.Mac800)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.background,
                                checkedTrackColor = MaterialTheme.colorScheme.primary,
                                checkedBorderColor = Color.Transparent,
                                uncheckedThumbColor = MaterialTheme.colorScheme.background,
                                uncheckedTrackColor = MaterialTheme.colorScheme.onTertiary,
                                uncheckedBorderColor = Color.Transparent,
                            ),
                            thumbContent = {
                                // fixed-size thumb, avoids shrinking
                                Box(
                                    modifier = Modifier
                                        .size(16.dp) // force constant thumb size
                                        .background(
                                            color = MaterialTheme.colorScheme.background,
                                            shape = CircleShape
                                        )
                                )
                            }
                        )
                    }
                )
                HorizontalDivider(thickness = 0.5.dp)
            } else {
                val label = when (action) {
                    SupportEvent.ConnectionSettings -> stringResource(R.string.support_connection_settings)
                    SupportEvent.GetAuthorizationKeys -> stringResource(R.string.support_get_auth_keys)
                    SupportEvent.GetConfiguration -> stringResource(R.string.support_get_config)
                    SupportEvent.UploadMasterKey -> stringResource(R.string.support_upload_master_key)
                    SupportEvent.ResetSupportPassword -> stringResource(R.string.support_reset_acquire_password)
                    SupportEvent.Exit -> stringResource(R.string.exit)
                    SupportEvent.Mac800 -> ""
                }

                val icon = when (action) {
                    SupportEvent.ConnectionSettings -> Icons.Default.Settings
                    SupportEvent.GetAuthorizationKeys -> Icons.Default.Lock
                    SupportEvent.GetConfiguration -> Icons.AutoMirrored.Filled.ExitToApp
                    SupportEvent.UploadMasterKey -> Icons.Default.Settings
                    SupportEvent.ResetSupportPassword -> Icons.Default.Lock
                    SupportEvent.Exit -> Icons.AutoMirrored.Filled.ExitToApp
                    SupportEvent.Mac800 -> Icons.Default.Settings
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
}
