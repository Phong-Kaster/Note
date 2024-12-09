package com.example.note.ui.fragment.notecreate

import androidx.compose.runtime.Stable
import com.example.note.domain.model.Note

@Stable
data class NoteCreateUiState(
    val note: Note = Note(),
)