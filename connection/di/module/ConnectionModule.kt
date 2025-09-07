package com.example.sampleproject.features.connection.di.module

import com.example.sampleproject.core.database.local.AppDatabase
import com.example.sampleproject.features.connection.data.repository.ConnectionRepositoryImpl
import com.example.sampleproject.features.connection.domain.repository.ConnectionRepository
import com.example.sampleproject.features.connection.domain.usecase.ConnectionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ConnectionModule {

    @Provides
    fun provideConnectionRepository(db: AppDatabase): ConnectionRepository {
        return ConnectionRepositoryImpl(db.connectionDao())
    }

    @Provides
    fun provideConnectionUseCase(repository: ConnectionRepository): ConnectionUseCase {
        return ConnectionUseCase(repository)
    }
}
