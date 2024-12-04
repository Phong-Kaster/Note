package com.example.note.ui.fragment

import androidx.compose.runtime.Stable
import com.example.note.domain.model.Note

@Stable
data class HomeUiState(
    val notes: List<Note> = emptyList(),
)