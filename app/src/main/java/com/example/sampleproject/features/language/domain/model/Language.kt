package com.example.sampleproject.features.language.domain.model

// Domain layer - no Compose imports here
enum class Language(val code: String) {
    ENGLISH("en"),
    PERSIAN("fa"),
    HOKHSHIANE("peo"),
    ARABIC("ar"),
    ITALIAN("it");

    companion object {
        fun fromCode(code: String) = entries.find { it.code == code } ?: ENGLISH
    }
}
