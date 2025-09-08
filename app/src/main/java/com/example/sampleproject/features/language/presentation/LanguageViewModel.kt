package com.example.sampleproject.features.language.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleproject.core.utils.LocaleHelper
import com.example.sampleproject.features.language.data.storage.LanguagePrefsDataSource
import com.example.sampleproject.features.language.domain.mapper.LanguageItemMapper
import com.example.sampleproject.features.language.domain.model.Language
import com.example.sampleproject.features.language.presentation.contract.LanguageEffect
import com.example.sampleproject.features.language.presentation.contract.LanguageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val prefs: LanguagePrefsDataSource
) : ViewModel() {

    private val _state = MutableStateFlow<LanguageState>(LanguageState.Loading)
    val state: StateFlow<LanguageState> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<LanguageEffect>()
    val effect = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            prefs.selectedLanguageFlow
                .distinctUntilChanged()
                .collect { lang ->
                    val selected = LanguageItemMapper.fromLanguage(lang)
                    val all = LanguageItemMapper.allLanguages
                        .filterNot { it.englishName == selected.englishName }

                    _state.value = LanguageState.AvailableLanguages(
                        selected = selected,
                        all = listOf(selected) + all
                    )

                    _effect.emit(LanguageEffect.LanguageChanged(lang))
                }
        }
    }

    val selectedLanguage: StateFlow<Language> = prefs.selectedLanguageFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        Language.ENGLISH // Default fallback
    )

    fun selectLanguage(context: Context, language: Language) {
        viewModelScope.launch {
            prefs.setSelectedLanguage(language)
            LocaleHelper.setLocale(context, language)
            _effect.emit(LanguageEffect.LanguageChanged(language))
        }
    }

    fun restartActivity(activity: Activity) {
        val intent = Intent(activity, activity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(intent)
        activity.overridePendingTransition(0, 0)
        activity.finish()
    }
}
