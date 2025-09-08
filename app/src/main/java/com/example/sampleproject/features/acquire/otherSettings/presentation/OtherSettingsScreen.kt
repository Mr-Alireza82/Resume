package com.example.sampleproject.features.acquire.otherSettings.presentation

import android.widget.Toast
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sampleproject.R
import com.example.sampleproject.core.utils.clearFocusOnTap
import com.example.sampleproject.features.language.presentation.LanguageDropdownMenu
import com.example.sampleproject.features.topbar.presentation.BackNavigationIcon
import com.example.sampleproject.features.topbar.presentation.TopBar
import io.github.composegears.valkyrie.fanAvaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtherSettingsScreen(navController: NavController) {

    var cardCount by remember { mutableIntStateOf(0) }
    var cardCountLimit = 5
    var cardCountError by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        snapAnimationSpec = spring(stiffness = Spring.StiffnessMedium)
    )

    val focusManager = LocalFocusManager.current

    fun validateCardCount(): Boolean {
        cardCountError = cardCount.toInt() <= cardCountLimit
        return cardCountError
    }

    val context = LocalContext.current

    fun handleSubmit() {
        focusManager.clearFocus()

        if (validateCardCount()) {
            Toast.makeText(context, "Clicked: true", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(context, "Clicked: false", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.title_other_settings),
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
        val view = LocalView.current
        val focusManager = LocalFocusManager.current

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .clickable(
                    // this ensures clicks outside inputs clear focus
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
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
                Spacer(modifier = Modifier.height(10.dp))

                val primary = MaterialTheme.colorScheme.primary
                val secondary = MaterialTheme.colorScheme.secondary

                val icon = remember(primary, secondary) {
                    fanAvaCard(primary = primary, secondary = secondary)
                }

                Icon(
                    imageVector = icon,
                    contentDescription = "FanAva Icon",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .width(240.dp)
                        .height(100.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = cardCount.toString(),
                    onValueChange = { if (it.toInt() <= 5) cardCount = it.toInt() },
                    label = { Text(stringResource(R.string.other_settings_input_label)) },
                    isError = cardCountError,

                    supportingText = {
                        Text(
                            if (cardCountError) "Invalid Card Count"
                            else cardCountLimit.toString()
                        )
                                     },

                    singleLine = true,
                    trailingIcon = {
                        if (cardCount == 0) {
                            IconButton(onClick = { cardCount = 0 }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    modifier = Modifier.width(100.dp)
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
