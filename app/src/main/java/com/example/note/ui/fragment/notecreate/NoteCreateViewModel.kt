package com.example.note.ui.fragment.notecreate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.configuration.Constant
import com.example.note.data.repository.NoteRepository
import com.example.note.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class NoteCreateViewModel
@Inject
constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private var _uiState = MutableStateFlow(NoteCreateUiState())
    val uiState: StateFlow<NoteCreateUiState> = _uiState.asStateFlow()

    init {
        setupDisplay()
    }

    private fun setupDisplay() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.titleTextField.setText(text = _uiState.value.note.title)
            _uiState.value.contentTextField.setText(text = _uiState.value.note.content)
        }
    }

    fun undo() {
        Log.d(TAG, "undo")
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.isTitleFocused) {
                _uiState.value.titleTextField.undo()
                return@launch
            }

            if (_uiState.value.isContentFocused) {
                _uiState.value.contentTextField.undo()
                return@launch
            }
        }
    }

    fun redo() {
        Log.d(TAG, "redo")
        viewModelScope.launch(Dispatchers.IO) {
            if (_uiState.value.isTitleFocused) {
                _uiState.value.titleTextField.redo()
                return@launch
            }

            if (_uiState.value.isContentFocused) {
                _uiState.value.contentTextField.redo()
                return@launch
            }
        }
    }

    fun done() {
        Log.d(TAG, "done")
        viewModelScope.launch(Dispatchers.IO) {
            val note = Note(
                uid = _uiState.value.note.uid,
                title = _uiState.value.titleTextField.currentValue.text,
                content = _uiState.value.contentTextField.currentValue.text,
                isTask = false,
                lastModified = Date(),
                categoryId = null,
            )
            noteRepository.insert(note = note)
        }
    }


    fun findNoteById(uid: Long) {
        if (uid == Constant.CREATE_NEW_NOTE) return

        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.findNoteById(uid).collect { it ->
                Log.d(TAG, "findNoteById = $it")
                _uiState.value.note = it
                setupDisplay()
            }
        }
    }

    fun updateTitleFocusChanged(isFocused: Boolean) {
        Log.d(TAG, "isTitleFocused = $isFocused")
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.isTitleFocused = isFocused
        }
    }

    fun updateContentFocusChanged(isFocused: Boolean) {
        Log.d(TAG, "isContentFocused = $isFocused")
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.isContentFocused = isFocused
        }
    }
}