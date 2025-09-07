package com.example.sampleproject.features.connection.presentation.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sampleproject.R
import com.example.sampleproject.core.utils.clearFocusOnTap
import com.example.sampleproject.features.connection.domain.model.ConnectionConfig
import com.example.sampleproject.features.connection.presentation.contract.ConnectionIntent
import com.example.sampleproject.features.connection.presentation.contract.ConnectionUiState
import com.example.sampleproject.features.connection.presentation.viewmodel.ConnectionViewModel
import com.example.sampleproject.features.language.presentation.LanguageDropdownMenu
import com.example.sampleproject.features.topbar.presentation.BackNavigationIcon
import com.example.sampleproject.features.topbar.presentation.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectionScreen(
    navController: NavController,
    viewModel: ConnectionViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ConnectionUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ConnectionUiState.Error -> {
            val message = (uiState as ConnectionUiState.Error).message
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: $message", color = Color.Red)
            }
        }
        is ConnectionUiState.Success -> {
            val config = (uiState as ConnectionUiState.Success).config

            var ip by rememberSaveable(config.ip) { mutableStateOf(config.ip) }
            var port by rememberSaveable(config.port) { mutableStateOf(config.port.toString()) }
            var nii by rememberSaveable(config.nii) { mutableStateOf(config.nii.toString()) }

            var ipErrorMessage by rememberSaveable { mutableStateOf("") }
            var portErrorMessage by rememberSaveable { mutableStateOf("") }
            var niiErrorMessage by rememberSaveable { mutableStateOf("") }

            var isIpError by rememberSaveable { mutableStateOf(false) }
            var isPortError by rememberSaveable { mutableStateOf(false) }
            var isNiiError by rememberSaveable { mutableStateOf(false) }

            val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
            val focusManager = LocalFocusManager.current
            val context = LocalContext.current

            fun validateIp(): Boolean {
                val parts = ip.split(".")
                val valid = parts.size == 4 && parts.all { it.isNotEmpty() && it.toIntOrNull() in 0..255 }
                if (ip.isEmpty()) {
                    ipErrorMessage = "Ip can not be empty"
                    isIpError = true
                } else if (ip.length !in 7..15) {
                    ipErrorMessage = "Ip length must be 7 to 15"
                    isIpError = true
                } else if (!valid) {
                    ipErrorMessage = "Ip must have at least 3 dots"
                    isIpError = true
                } else {
                    isIpError = false
                }
                return !isIpError
            }

            fun validatePort(): Boolean {
                val value = port.toIntOrNull()
                if (value == null) {
                    portErrorMessage = "Port can not be empty"
                    isPortError = true
                } else if (value !in 1..65535) {
                    portErrorMessage = "Port out of range"
                    isPortError = true
                } else {
                    isPortError = false
                }
                return !isPortError
            }

            fun validateNii(): Boolean {
                val value = nii.toIntOrNull()
                return if (value == null) {
                    niiErrorMessage = "Nii can not be empty"
                    isNiiError = true
                    false
                } else {
                    isNiiError = false
                    true
                }
            }

            fun validateAll(): Boolean = validateIp() && validatePort() && validateNii()

            fun handleSubmit() {
                focusManager.clearFocus()
                val isValid = validateAll()
                if (!isValid) return
                val portVal = port.toIntOrNull() ?: return
                val niiVal = nii.toIntOrNull() ?: return
                viewModel.processIntent(
                    ConnectionIntent.SaveConfig(
                        ConnectionConfig(ip = ip, port = portVal, nii = niiVal)
                    )
                )
                Toast.makeText(context, "✅ Saved config!", Toast.LENGTH_SHORT).show()
            }

            Scaffold(
                topBar = {
                    TopBar(
                        title = stringResource(id = R.string.title_connection),
                        scrollBehavior = scrollBehavior,
                        navigationIcon = { BackNavigationIcon(navController = navController) },
                        actions = { LanguageDropdownMenu() },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            scrolledContainerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = Color.White
                        )
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { innerPadding ->
                val view = LocalView.current
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                            focusManager.clearFocus()
                        }
                        .clearFocusOnTap(view, focusManager)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = 16.dp)
                            .windowInsetsPadding(WindowInsets.ime),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = ip,
                            onValueChange = { newValue ->
                                val cleaned = newValue.filterNot { it == ',' }
                                if (cleaned.length <= 15) ip = cleaned
                            },
                            label = { Text(stringResource(R.string.connection_destination_ip)) },
                            isError = isIpError,
                            supportingText = { Text(if (isIpError) ipErrorMessage else "${ip.length}/15") },
                            singleLine = true,
                            trailingIcon = {
                                if (ip.isNotEmpty()) {
                                    IconButton(onClick = { ip = "" }) {
                                        Icon(Icons.Default.Clear, contentDescription = "Clear")
                                    }
                                }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = port,
                            onValueChange = { newValue ->
                                val cleaned = newValue.filterNot { it == ',' || it == '.' }
                                if (cleaned.length <= 5) port = cleaned
                            },
                            label = { Text(stringResource(R.string.connection_destination_port)) },
                            isError = isPortError,
                            supportingText = { Text(if (isPortError) portErrorMessage else "${port.length}/5") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = nii,
                            onValueChange = { newValue ->
                                val cleaned = newValue.filterNot { it == ',' || it == '.' }
                                if (newValue.length <= 4) nii = cleaned
                            },
                            label = { Text(stringResource(R.string.connection_destination_nii)) },
                            isError = isNiiError,
                            supportingText = { Text(if (isNiiError) niiErrorMessage else "${nii.length}/4") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal,
                                imeAction = ImeAction.Done
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { handleSubmit() },
                            contentPadding = PaddingValues(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text(stringResource(R.string.confirm), color = Color.White, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}
