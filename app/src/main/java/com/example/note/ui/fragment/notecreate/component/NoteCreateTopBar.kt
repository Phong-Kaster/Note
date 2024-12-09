package com.example.note.ui.fragment.notecreate.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.note.R

@Composable
fun NoteCreateTopBar(
    onBack: () -> Unit = {},
    onUndo: () -> Unit = {},
    onRedo: () -> Unit = {},
    onDone: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        IconButton(
            onClick = onBack,
            content = {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    tint = Color.White,
                    contentDescription = "Back",
                )
            },
            modifier = Modifier
                .align(Alignment.CenterStart)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            IconButton(
                onClick = onUndo,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_undo),
                        tint = Color.White,
                        contentDescription = "Back",
                    )
                },
                modifier = Modifier
            )

            IconButton(
                onClick = onRedo,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_redo),
                        tint = Color.White,
                        contentDescription = "Back",
                    )
                },
                modifier = Modifier
            )

            IconButton(
                onClick = onDone,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.ic_check),
                        tint = Color.White,
                        contentDescription = "Back",
                    )
                },
                modifier = Modifier
            )
        }
    }
}

@Preview
@Composable
private fun PreviewNoteCreateTopBar() {
    NoteCreateTopBar()
}