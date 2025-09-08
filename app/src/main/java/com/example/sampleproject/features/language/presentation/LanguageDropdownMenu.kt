package com.example.sampleproject.features.language.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleproject.core.utils.findActivity
import com.example.sampleproject.features.language.domain.model.Language
import com.example.sampleproject.features.language.presentation.contract.LanguageState

@Composable
fun LanguageDropdownMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val activity = remember(context) { context.findActivity() }
    var expanded by remember { mutableStateOf(false) }

    val viewModel: LanguageViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    when (val currentState = state) {
        is LanguageState.Loading -> {
            // Show loading if needed
        }

        is LanguageState.AvailableLanguages -> {
            val selected = currentState.selected
            val allLanguages = currentState.all

            Box(modifier = modifier.wrapContentSize(Alignment.TopEnd)) {
                Row(
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberVectorPainter(selected.icon),
                        contentDescription = "${selected.englishName} CardItems",
                        modifier = Modifier.padding(end = 8.dp)
                            .size(24.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = selected.code,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        overflow = Ellipsis
                    )
                    Spacer(Modifier.width(6.dp))
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    allLanguages.forEach { language ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    language.nativeName,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.W500
                                    ),
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            },
                            onClick = {
                                expanded = false
                                val selectedLang = Language.fromCode(language.code)
                                activity?.let {
                                    viewModel.selectLanguage(it, selectedLang)
                                }
                            },
                            leadingIcon = {
                                Image(
                                    painter = rememberVectorPainter(language.icon),
                                    contentDescription = language.englishName,
                                    modifier = Modifier.size(26.dp).clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
