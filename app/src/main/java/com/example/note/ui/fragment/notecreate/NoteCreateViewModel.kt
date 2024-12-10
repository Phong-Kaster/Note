package com.example.note.ui.fragment.notecreate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
constructor() : ViewModel() {
    private val TAG = this.javaClass.simpleName

    private var _uiState = MutableStateFlow(NoteCreateUiState())
    val uiState: StateFlow<NoteCreateUiState> = _uiState.asStateFlow()

    fun undo() {
        Log.d(TAG, "undo")
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.titleTextField.undo()
            _uiState.value.contentTextField.undo()
        }
    }

    fun redo() {
        Log.d(TAG, "redo")
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value.titleTextField.redo()
            _uiState.value.contentTextField.redo()
        }
    }
    fun done(){
        Log.d(TAG, "done")
        viewModelScope.launch(Dispatchers.IO){
            val note = Note(
                title = _uiState.value.titleTextField.currentValue.text,
                content = _uiState.value.contentTextField.currentValue.text,
                isTask = false,
                lastModified = Date(),
                categoryId = null,
            )

            Log.d(TAG, "done - note = $note")
        }
    }
}