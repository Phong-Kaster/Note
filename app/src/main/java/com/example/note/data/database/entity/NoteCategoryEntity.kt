package com.example.note.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_note_category")
class NoteCategoryEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "name") val name: String? = null,
    )