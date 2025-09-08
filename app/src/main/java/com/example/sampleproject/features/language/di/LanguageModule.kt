package com.example.sampleproject.features.language.di

import android.content.Context
import com.example.sampleproject.features.language.data.storage.LanguagePrefsDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LanguageModule {

    @Provides
    @Singleton
    fun provideLanguagePrefsDataSource(
        @ApplicationContext context: Context
    ): LanguagePrefsDataSource = LanguagePrefsDataSource(context)

}
