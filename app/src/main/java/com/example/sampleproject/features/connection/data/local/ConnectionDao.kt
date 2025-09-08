package com.example.sampleproject.features.connection.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ConnectionDao {
    @Query("SELECT * FROM connection LIMIT 1")
    suspend fun get(): ConnectionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ConnectionEntity)

    @Update
    suspend fun update(entity: ConnectionEntity)

    @Query("UPDATE connection SET ip = :ip WHERE id = 1")
    suspend fun updateIp(ip: String)

    @Query("UPDATE connection SET port = :port WHERE id = 1")
    suspend fun updatePort(port: Int)

    @Query("UPDATE connection SET nii = :nii WHERE id = 1")
    suspend fun updateNii(nii: Int)

    @Query("SELECT ip FROM connection WHERE id = 1")
    suspend fun getIp(): String?

    @Query("SELECT port FROM connection WHERE id = 1")
    suspend fun getPort(): Int?

    @Query("SELECT nii FROM connection WHERE id = 1")
    suspend fun getNii(): Int?
}