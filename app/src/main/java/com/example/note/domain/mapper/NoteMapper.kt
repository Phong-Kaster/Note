package com.example.note.domain.mapper

import com.example.note.data.database.entity.NoteEntity
import com.example.note.domain.model.Note

object NoteMapper {
    fun Note.toEntity(): NoteEntity {
        return NoteEntity(
            uid = this.uid,
            title = this.title,
            content = this.content,
            isTask = this.isTask,
            lastModified = this.lastModified,
            categoryId = this.categoryId,
        )
    }

    fun NoteEntity.toModel(): Note {
        return Note(
            uid = this.uid,
            title = this.title ?: "",
            isTask = this.isTask,
            content = this.content ?: "",
            lastModified = this.lastModified,
        )
    }
}