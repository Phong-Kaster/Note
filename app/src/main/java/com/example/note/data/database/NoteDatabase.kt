package com.example.note.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.note.data.database.converter.DateConverter
import com.example.note.data.database.dao.NoteDao
import com.example.note.data.database.entity.NoteCategoryEntity
import com.example.note.data.database.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, NoteCategoryEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}