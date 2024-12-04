package com.example.note.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface  NoteDao {
    // CREATE
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: NoteEntity): Long

    // READ
    @Query("SELECT * FROM table_note WHERE table_note.uid LIKE :uid")
    suspend fun findById(uid: Long): List<NoteEntity>

    @Query("SELECT * FROM table_note")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM table_note")
     fun getAllFlow(): Flow<List<NoteEntity>>

    // UPDATE
    @Update
    suspend fun update(entity: NoteEntity)

    // DELETE
    @Delete
    suspend fun delete(entity: NoteEntity)

    @Delete
    suspend fun deleteMultiple(vararg entity: NoteEntity)

    @Query("DELETE FROM table_note")
    suspend fun clear()
}