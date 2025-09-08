package com.example.sampleproject.features.keys.di.module

import android.content.Context
import com.example.sampleproject.features.keys.data.local.AppletPreferences
import com.example.sampleproject.features.keys.data.repository.AppletRepositoryImpl
import com.example.sampleproject.features.keys.domain.repository.AppletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KeysModule {

    @Provides
    @Singleton
    fun provideAppletPreferences(@ApplicationContext ctx: Context): AppletPreferences =
        AppletPreferences(ctx)

    @Provides
    @Singleton
    fun provideAppletRepository(pref: AppletPreferences): AppletRepository =
        AppletRepositoryImpl(pref)
}
