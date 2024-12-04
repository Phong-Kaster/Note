package com.example.note.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "table_note")
class NoteEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "title") val title: String? = null,
    @ColumnInfo(name = "content") val content: String? = null,
    @ColumnInfo(name = "isTask") val isTask: Boolean = false,
    @ColumnInfo(name = "lastModified") val lastModified: Date = Date(),
    @ColumnInfo(name = "categoryId") val categoryId: Long? = null,
)