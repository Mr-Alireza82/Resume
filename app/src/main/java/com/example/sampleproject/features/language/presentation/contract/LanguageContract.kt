package com.example.sampleproject.features.language.presentation.contract

import com.example.sampleproject.features.language.data.repository.LanguageItem
import com.example.sampleproject.features.language.domain.model.Language

sealed interface LanguageState {
    object Loading : LanguageState

    data class AvailableLanguages(
        val selected: LanguageItem,
        val all: List<LanguageItem>
    ) : LanguageState
}

sealed interface LanguageEffect {
    data class LanguageChanged(val language: Language) : LanguageEffect
}
