package com.example.note.domain.model

import androidx.compose.runtime.Stable
import java.util.Date

@Stable
data class Note(
    val uid: Long,
    val title: String = "",
    val content: String = "",
    val isTask: Boolean = false,
    val lastModified: Date = Date(),
    val categoryId: Long? = null,
) {
    companion object {
        fun getFakeNote() : Note {
            return Note(
                uid = 1,
                title = "Phong Kaster",
                content = "I am not gorgeous in society, I am beautiful in my mind",
                lastModified = Date(),
                categoryId = NoteCategory.getFakeCategory().uid,
            )
        }
    }
}