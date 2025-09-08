package com.example.sampleproject.core.database.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject.core.database.local.terminal.data.TerminalDao
import com.example.sampleproject.core.database.local.terminal.data.TerminalEntity
import com.example.sampleproject.features.connection.data.local.ConnectionDao
import com.example.sampleproject.features.connection.data.local.ConnectionEntity

@Database(
    entities = [TerminalEntity::class, ConnectionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun terminalDao(): TerminalDao
    abstract fun connectionDao(): ConnectionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(application: Application): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    "app_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
