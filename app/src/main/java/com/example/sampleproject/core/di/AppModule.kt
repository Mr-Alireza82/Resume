package com.example.sampleproject.core.di

import android.app.Application
import com.example.sampleproject.core.database.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDatabase(app: Application): AppDatabase = AppDatabase.getInstance(app)

}
