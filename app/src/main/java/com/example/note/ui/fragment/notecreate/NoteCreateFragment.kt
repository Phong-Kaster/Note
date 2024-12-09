package com.example.note.ui.fragment.notecreate

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.ui.fragment.notecreate.component.NoteCreateTopBar
import com.example.note.util.NavigationUtil.safeNavigateUp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteCreateFragment : CoreFragment() {
    @Composable
    override fun ComposeView() {
        super.ComposeView()
        NoteCreateLayout(
            onBack = { safeNavigateUp() },
            onUndo = {},
            onRedo = {},
            onDone = {},
        )
    }
}

@Composable
fun NoteCreateLayout(
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

        }
    )
}

@Preview
@Composable
private fun PreviewNoteCreateLayout() {
    NoteCreateLayout()
}