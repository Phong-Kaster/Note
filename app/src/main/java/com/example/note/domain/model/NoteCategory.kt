package com.example.note.domain.model

import androidx.compose.runtime.Stable

@Stable
data class NoteCategory(
    val uid: Long,
    val name: String = "",
) {
    companion object {
        fun getFakeCategory(): NoteCategory {
            return NoteCategory(
                uid = 1,
                name = "Work",
            )
        }
    }
}