package com.example.sampleproject.core.di

import android.app.Application
import android.content.Context
import com.example.sampleproject.core.utils.LocaleHelper
import com.example.sampleproject.features.language.domain.model.Language
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun attachBaseContext(newBase: Context) {
        val langCode = newBase.getSharedPreferences("fast_language", Context.MODE_PRIVATE)
            ?.getString("lang_code", Language.ENGLISH.code) ?: Language.ENGLISH.code

        val localeUpdatedContext = LocaleHelper.setLocale(newBase, Language.fromCode(langCode))
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onCreate() {
        super.onCreate()
        ApplicationHolder.applicationContext = this
    }
}
