package com.example.sampleproject.features.startup.di

import com.example.sampleproject.core.config.p3.DeviceConfig
import com.example.sampleproject.core.database.local.AppDatabase
import com.example.sampleproject.features.startup.domain.repository.InitializeAppUseCase
import com.example.sampleproject.features.terminal.data.TerminalRepositoryImpl
import com.example.sampleproject.features.terminal.domain.TerminalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object StartupModule {

    @Provides
    fun provideTerminalRepository(db: AppDatabase): TerminalRepository =
        TerminalRepositoryImpl(db.terminalDao())

    @Provides
    fun provideInitializeAppUseCase(repository: TerminalRepository): InitializeAppUseCase =
        InitializeAppUseCase(DeviceConfig.DEFAULT_MAC_KEY, repository)

}
