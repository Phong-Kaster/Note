package com.example.note.ui.fragment.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.repository.NoteRepository
import com.example.note.ui.fragment.note.NoteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private var _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState.asStateFlow()

    init {
        collectNotes()
    }

    private fun collectNotes() {
        viewModelScope.launch {
            noteRepository.getAllFlow().collect { notes ->
                val listOfNote = notes.filter { it.isTask }
                _uiState.value = _uiState.value.copy(notes = listOfNote)
            }
        }
    }
}