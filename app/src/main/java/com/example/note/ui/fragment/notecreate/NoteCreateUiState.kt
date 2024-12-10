package com.example.note.ui.fragment.notecreate

import androidx.compose.runtime.Stable
import com.example.note.domain.model.HistoricalTextField

@Stable
class NoteCreateUiState{
    val titleTextField = HistoricalTextField()
    val contentTextField = HistoricalTextField()
}