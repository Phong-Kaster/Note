package com.example.note.ui.fragment.notecreate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.note.domain.model.HistoricalTextField
import com.example.note.domain.model.Note

@Stable
class NoteCreateUiState {
    var isTypingMode by mutableStateOf(false)
    var note by mutableStateOf(Note())
    val titleTextField = HistoricalTextField()
    val contentTextField = HistoricalTextField()

}