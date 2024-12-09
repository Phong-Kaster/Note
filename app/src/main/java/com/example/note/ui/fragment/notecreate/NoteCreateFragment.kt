package com.example.note.ui.fragment.notecreate

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.ui.fragment.notecreate.component.NoteCreateSectionContent
import com.example.note.ui.fragment.notecreate.component.NoteCreateSectionTitle
import com.example.note.ui.fragment.notecreate.component.NoteCreateTopBar
import com.example.note.util.NavigationUtil.safeNavigateUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteCreateFragment : CoreFragment() {

    private val viewModel: NoteCreateViewModel by viewModels()

    @Composable
    override fun ComposeView() {
        super.ComposeView()
        NoteCreateLayout(
            noteTitle = viewModel.noteTitle,
            noteContent = viewModel.noteContent,
            onBack = { safeNavigateUp() },
            onUndo = { viewModel.undo() },
            onRedo = { viewModel.redo() },
            onDone = {
                Toast.makeText(requireContext(), "${viewModel.noteTitle.text}", Toast.LENGTH_SHORT).show()
            },
        )
    }
}

@Composable
fun NoteCreateLayout(
    noteTitle: TextFieldState,
    noteContent: TextFieldState,
    onBack: () -> Unit = {},
    onUndo: () -> Unit = {},
    onRedo: () -> Unit = {},
    onDone: () -> Unit = {},
) {

    CoreLayout(
        topBar = {
            NoteCreateTopBar(
                onBack = onBack,
                onUndo = onUndo,
                onRedo = onRedo,
                onDone = onDone,
            )
        },
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                item(key = "title") { NoteCreateSectionTitle(title = noteTitle) }
                item(key = "content") { NoteCreateSectionContent(content = noteContent) }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewNoteCreateLayout() {
    NoteCreateLayout(
        noteTitle = TextFieldState(),
        noteContent = TextFieldState(),
    )
}