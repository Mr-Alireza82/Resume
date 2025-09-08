package com.example.sampleproject.features.main.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainBodyContent(innerPadding: PaddingValues, viewModel: MainViewModel = hiltViewModel()) {
    val focusManager = LocalFocusManager.current

    MainBodyLayout(innerPadding, viewModel)
}

object NativeBridge {
    @JvmStatic
    external fun stringFromJNI(): String

    init {
        System.loadLibrary("sampleproject")
    }
}
