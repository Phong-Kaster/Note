package com.example.note.ui.fragment.notecreate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.configuration.Constant
import com.example.note.ui.fragment.notecreate.component.SectionContent
import com.example.note.ui.fragment.notecreate.component.SectionTitle
import com.example.note.ui.fragment.notecreate.component.NoteCreateTopBar
import com.example.note.util.NavigationUtil.safeNavigateUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteCreateFragment : CoreFragment() {

    private val viewModel: NoteCreateViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        collectPassedData()
    }

    /**
     * if Note id == 0 then create new note
     * if Note id > 0 then update note
     * */
    private fun collectPassedData() {
        val noteId = arguments?.getLong(Constant.NOTE_ID) ?: return
        viewModel.findNoteById(noteId)
    }

    @Composable
    override fun ComposeView() {
        super.ComposeView()
        NoteCreateLayout(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            onBack = { safeNavigateUp() },
            onUndo = { viewModel.undo() },
            onRedo = { viewModel.redo() },
            onDone = { viewModel.done() },
        )
    }
}

@Composable
fun NoteCreateLayout(
    uiState: NoteCreateUiState = NoteCreateUiState(),
    onBack: () -> Unit = {},
    onUndo: () -> Unit = {},
    onRedo: () -> Unit = {},
    onDone: () -> Unit = {},
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    CoreLayout(
        topBar = {
            NoteCreateTopBar(
                onBack = onBack,
                onUndo = onUndo,
                onRedo = onRedo,
                onDone = {
                    onDone()
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
            )
        },
        content = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                item(key = "title") { SectionTitle(textField = uiState.titleTextField) }
                item(key = "content") { SectionContent(textField = uiState.contentTextField) }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewNoteCreateLayout() {
    NoteCreateLayout(
        uiState = NoteCreateUiState()
    )
}