package com.example.note.ui.fragment.note

import androidx.compose.runtime.Stable
import com.example.note.domain.model.Note

@Stable
data class NoteUiState(
    val notes: List<Note> = emptyList(),
)