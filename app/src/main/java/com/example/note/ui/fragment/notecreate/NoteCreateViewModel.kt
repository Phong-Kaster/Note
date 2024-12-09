package com.example.note.ui.fragment.notecreate

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteCreateViewModel
@Inject
constructor() : ViewModel() {
    private val TAG = this.javaClass.simpleName
}