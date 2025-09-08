package com.example.sampleproject.core.database.local.terminal.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TerminalDao {
    @Query("SELECT * FROM terminal LIMIT 1")
    suspend fun get(): TerminalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TerminalEntity)

    @Update
    suspend fun update(entity: TerminalEntity)

    @Query("UPDATE terminal SET macKey = :macKey WHERE id = 1")
    suspend fun updateMacKey(macKey: ByteArray)

    @Query("SELECT macKey FROM terminal WHERE id = 1")
    suspend fun getMacKey(): ByteArray?
}
