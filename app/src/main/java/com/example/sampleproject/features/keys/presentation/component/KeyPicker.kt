package com.example.sampleproject.features.keys.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleproject.features.keys.presentation.contract.AppletState
import com.example.sampleproject.features.keys.presentation.viewmodel.KeyViewModel

@Composable
fun KeyAppletDropdown(modifier: Modifier = Modifier) {
    val viewModel: KeyViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    var expanded by remember { mutableStateOf(false) }

    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val horizontalPadding = (screenWidthDp * 0.2f).dp

    when (state) {
        is AppletState.Loading -> {
            // placeholder
            Text("Loading...", modifier = modifier.padding(horizontal = horizontalPadding))
        }
        is AppletState.Error -> {
            Text("Error: ${(state as AppletState.Error).message}")
        }
        is AppletState.Success -> {
            val s = state as AppletState.Success
            val selected = s.selected

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = horizontalPadding), horizontalAlignment = Alignment.CenterHorizontally) {

                Box(modifier = modifier.fillMaxWidth()) {
                    Text(
                        text = selected.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = !expanded }
                            .padding(12.dp)
                    )

                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        s.all.forEach { applet ->
                            DropdownMenuItem(text = { Text(applet.name) }, onClick = {
                                expanded = false
                                viewModel.onSelect(applet)
                            })
                        }
                    }
                }
            }
        }
    }
}
