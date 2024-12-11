package com.example.note.ui.fragment.notecreate

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.note.domain.model.HistoricalTextField
import com.example.note.domain.model.Note

@Stable
class NoteCreateUiState {

    var note by mutableStateOf(Note())
    val titleTextField = HistoricalTextField()
    val contentTextField = HistoricalTextField()
    var isTitleFocused by mutableStateOf(false)
    var isContentFocused by mutableStateOf(false)

    val isTypingMode: Boolean
        get() = isTitleFocused || isContentFocused
}