package com.example.note.ui.fragment.notecreate

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteCreateViewModel
@Inject
constructor() : ViewModel() {
    private val TAG = this.javaClass.simpleName

//    private var _uiState = MutableStateFlow(NoteCreateUiState())
//    val uiState: StateFlow<NoteCreateUiState> = _uiState.asStateFlow()

    private val _note = MutableStateFlow(Note())
    val note: StateFlow<Note> = _note.asStateFlow()

    val noteTitle: TextFieldState = TextFieldState()
    val noteContent: TextFieldState = TextFieldState()

    fun updateTitle(title: String){
        viewModelScope.launch(Dispatchers.IO){
            _note.value = _note.value.copy(title = title)
        }
    }

    fun updateContent(content: String){
        viewModelScope.launch(Dispatchers.IO){
            _note.value = _note.value.copy(content = content)
        }
    }

    fun undo(){
        viewModelScope.launch(Dispatchers.IO){
            if (noteTitle.text.isNotEmpty()) {
                val nextText = noteTitle.text
            }
        }
    }
}