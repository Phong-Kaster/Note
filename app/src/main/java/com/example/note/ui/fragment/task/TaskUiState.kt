package com.example.note.ui.fragment.task

import androidx.compose.runtime.Stable
import com.example.note.domain.model.Note

@Stable
data class TaskUiState(
    val notes: List<Note> = emptyList()
)