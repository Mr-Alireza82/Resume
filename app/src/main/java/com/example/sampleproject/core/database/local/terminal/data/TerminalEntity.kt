package com.example.sampleproject.core.database.local.terminal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terminal")
data class TerminalEntity(
    @PrimaryKey val id: Int = 1,
    val macKey: ByteArray
)
