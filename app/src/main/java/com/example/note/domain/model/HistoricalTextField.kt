package com.example.note.domain.model

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue

class HistoricalTextField {

    private val defaultValue = TextFieldValue()

    /** List of Text Field stores the value that users are typing on the screen*/
    private val textFieldValues = mutableListOf<TextFieldValue>()

    /** List of Text Field stores the value that users have just removed on the screen while they are typing*/
    private val undoTextFieldValues = mutableListOf<TextFieldValue>()

    /** Current value represents for the value that displays on the screen*/
    var currentValue: TextFieldValue by mutableStateOf(defaultValue)

    @SuppressLint("NewApi")
    fun undo() {
        if (textFieldValues.isEmpty()) return

        // get the last character & remove
        val lastItem = textFieldValues.last()
        textFieldValues.removeLast()

        // get the last character of the list after remove the previous character
        currentValue = textFieldValues.lastOrNull() ?: defaultValue

        // add the last character to the undo list
        undoTextFieldValues.add(lastItem)
    }


    @SuppressLint("NewApi")
    fun redo() {
        if (undoTextFieldValues.isEmpty()) return

        try {
            // get the last element of undo text field values
            val lastItem = undoTextFieldValues.last()

            // set current value equals undo text field value
            currentValue = undoTextFieldValues.removeLast()
            textFieldValues.add(lastItem)

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun update(newValue: TextFieldValue) {
        if (textFieldValues.isEmpty() || (currentValue.text != newValue.text)) {
            textFieldValues.add(newValue)
        }
        currentValue = newValue
    }

    fun setText(text: String) {
        currentValue = TextFieldValue(text = text)
    }
}