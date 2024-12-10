package com.example.note.ui.fragment.notecreate.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.core.LocalTheme
import com.example.note.R
import com.example.note.domain.model.HistoricalTextField
import com.example.note.ui.theme.customizedTextStyle

@Composable
fun NoteCreateSectionContent(
    textField: HistoricalTextField,
) {
    val currentTitle =textField.currentValue

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = currentTitle,
            onValueChange = { textField.update(it) },
            textStyle = customizedTextStyle(
                fontSize = 24,
                fontWeight = 600,
                color = LocalTheme.current.textColor,
            ),
            cursorBrush = SolidColor(Color.Cyan),
            decorationBox = { innerTextField ->
                // Because the decorator is used, the whole Row gets the same behaviour as the internal
                // input field would have otherwise. For example, there is no need to add a
                // `Modifier.clickable` to the Row anymore to bring the text field into focus when user
                // taps on a larger text field area which includes paddings and the icon areas.
                if (currentTitle.text.isEmpty()) {
                    Text(
                        text = "${stringResource(R.string.start_typing)}...",
                        style = customizedTextStyle(
                            fontSize = 24,
                            fontWeight = 600,
                            color = Color.DarkGray,
                        ),
                    )
                }
                innerTextField()
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PreviewNoteCreateSectionContent() {
    NoteCreateSectionContent(
        textField = HistoricalTextField(),
    )
}