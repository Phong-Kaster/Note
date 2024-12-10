package com.example.note.ui.fragment.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private var _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState.asStateFlow()

    init {
        collectNotes()
    }

    private fun collectNotes() {
        viewModelScope.launch {
            noteRepository.getAllFlow().collect { notes ->
                _uiState.value = _uiState.value.copy(notes = notes)
            }
        }
    }
}