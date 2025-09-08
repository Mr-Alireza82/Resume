package com.example.sampleproject.core.utils

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import com.example.sampleproject.features.language.domain.model.Language
import java.util.Locale

object LocaleHelper {
    fun setLocale(context: Context, language: Language): Context {
        val locale = Locale.forLanguageTag(language.code)
        Locale.setDefault(locale)

        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = android.os.LocaleList(locale)
                context
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                val config = Configuration(context.resources.configuration)
                config.setLocale(locale)
                config.setLocales(android.os.LocaleList(locale))
                config.setLayoutDirection(locale) // ✅ this line is critical
                context.createConfigurationContext(config)
            }

            else -> {
                val config = Configuration(context.resources.configuration)
                config.setLocale(locale)
                config.setLayoutDirection(locale) // ✅ required for RTL to apply
                @Suppress("DEPRECATION")
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
                context
            }
        }
    }
}
