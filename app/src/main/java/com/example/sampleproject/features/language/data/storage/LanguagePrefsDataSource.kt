package com.example.sampleproject.features.language.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.sampleproject.features.language.domain.model.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.languageDataStore by preferencesDataStore(name = "language_prefs")

class LanguagePrefsDataSource(private val context: Context) {

    private val selectedLanguage = stringPreferencesKey("selected_language")

    val selectedLanguageFlow: Flow<Language> = context.languageDataStore.data.map { prefs ->
        val code = prefs[selectedLanguage] ?: Language.ENGLISH.code
        Language.Companion.fromCode(code)
    }

    suspend fun setSelectedLanguage(language: Language) {
        context.languageDataStore.edit { prefs ->
            prefs[selectedLanguage] = language.code
        }

        context.getSharedPreferences("fast_language", Context.MODE_PRIVATE)
            .edit()
            .putString("lang_code", language.code)
            .apply()
    }
}
