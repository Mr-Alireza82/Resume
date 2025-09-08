package com.example.sampleproject.features.connection.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "connection")
data class ConnectionEntity(
    @PrimaryKey val id: Int = 1,
    val ip: String,
    val port: Int,
    val nii: Int
)
