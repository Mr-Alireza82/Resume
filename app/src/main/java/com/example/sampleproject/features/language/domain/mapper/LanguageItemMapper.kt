package com.example.sampleproject.features.language.domain.mapper

import com.example.sampleproject.features.language.data.repository.LanguageItem
import com.example.sampleproject.features.language.domain.model.Language
import com.example.sampleproject.ui.icons.countries.flag.derafshShahbaz
import com.example.sampleproject.ui.icons.countries.flag.iran
import com.example.sampleproject.ui.icons.countries.flag.italy
import com.example.sampleproject.ui.icons.countries.flag.saudiArabia
import com.example.sampleproject.ui.icons.countries.flag.usa

object LanguageItemMapper {
    fun fromLanguage(lang: Language): LanguageItem {
        return when (lang) {
            Language.ENGLISH -> LanguageItem("en", "English", "English", usa())
            Language.PERSIAN -> LanguageItem("fa", "فارسی", "Persian", iran())
            Language.HOKHSHIANE -> LanguageItem("peo", "𐎱𐎠𐎼𐎿𐎹", "Hokhshiane", derafshShahbaz())
            Language.ARABIC -> LanguageItem("ar", "العربية", "Arabic", saudiArabia())
            Language.ITALIAN -> LanguageItem("it", "Italiano", "Italian", italy())
        }
    }

    val allLanguages = listOf(
        fromLanguage(Language.ENGLISH),
        fromLanguage(Language.PERSIAN),
        fromLanguage(Language.HOKHSHIANE),
        fromLanguage(Language.ARABIC),
        fromLanguage(Language.ITALIAN)
    )
}
