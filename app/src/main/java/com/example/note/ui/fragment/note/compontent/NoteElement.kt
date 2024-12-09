package com.example.note.ui.fragment.note.compontent

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.core.LocalTheme
import com.example.note.domain.model.Note
import com.example.note.ui.theme.customizedTextStyle
import com.example.note.util.DateUtil
import com.example.note.util.DateUtil.formatWithPattern

@Composable
fun NoteElement(
    note: Note,
    onClick: () -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .clickable { onClick() }
            .background(
                color = Color(0xFF212E44),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Text(
            text = note.title,
            style = customizedTextStyle(
                fontSize = 18,
                fontWeight = 600,
                color = Color.White,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(
            text = note.content,
            style = customizedTextStyle(
                fontSize = 14,
                fontWeight = 500,
                color = Color.LightGray,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        Text(
            text = note.lastModified.formatWithPattern(pattern = DateUtil.PATTERN_EEE_MMM_dd),
            style = customizedTextStyle(
                fontSize = 14,
                fontWeight = 500,
                color = Color.LightGray,
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee(iterations = Int.MAX_VALUE)
        )
    }
}

@Preview
@Composable
private fun PreviewNoteElement() {
    Column(
        modifier = Modifier
            .background(LocalTheme.current.background)
            .padding(16.dp)
    ) {
        NoteElement(
            note = Note.getFakeNote()
        )
    }

}