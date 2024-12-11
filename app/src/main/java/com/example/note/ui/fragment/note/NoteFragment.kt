package com.example.note.ui.fragment.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.R
import com.example.note.configuration.Constant
import com.example.note.domain.model.Note
import com.example.note.ui.component.CoreBottomBar
import com.example.note.ui.component.CoreExpandableFloatingButton
import com.example.note.ui.fragment.note.compontent.NoteElement
import com.example.note.ui.component.TopBar
import com.example.note.ui.theme.customizedTextStyle
import com.example.note.util.NavigationUtil.safeNavigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : CoreFragment() {

    private val viewModel: NoteViewModel by viewModels()

    @Composable
    override fun ComposeView() {
        super.ComposeView()
        HomeLayout(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
            onCreateNote = {
                safeNavigate(
                    destination = R.id.toCreateNote,
                    bundle = bundleOf(Constant.NOTE_ID to Constant.CREATE_NEW_NOTE)
                )
            },
            onOpenNote = {note ->
                safeNavigate(
                    destination = R.id.toCreateNote,
                    bundle = bundleOf(Constant.NOTE_ID to note.uid)
                )
            }
        )
    }
}

@Composable
fun HomeLayout(
    uiState: NoteUiState,
    onCreateNote: () -> Unit = {},
    onOpenNote:(Note) -> Unit= {},
) {

    // for expandable floating action button
    val state = rememberLazyListState()
    val isCollapsed by remember { derivedStateOf { state.firstVisibleItemIndex > 0 } }


    CoreLayout(
        //showLoading = uiState.notes.isEmpty(),
        topBar = {
            TopBar(
                isCollapsed = isCollapsed,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        bottomBar = { CoreBottomBar() },
        floatingActionButton = {
            CoreExpandableFloatingButton(
                extended = isCollapsed,
                onClick = onCreateNote
            )
        },
        modifier = Modifier,
        content = {
            LazyColumn(
                state = state,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
            ) {
                item(key = "Note") {
                    Text(
                        text = stringResource(R.string.note),
                        style = customizedTextStyle(
                            fontSize = 32,
                            fontWeight = 800,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                itemsIndexed(
                    items = uiState.notes,
                    key = { index: Int, item: Note -> "$index" },
                    itemContent = { index, note ->
                        NoteElement(
                            note = note,
                            onClick = { onOpenNote(note) },
                        )
                    }
                )
            }
        },
    )
}

@Preview
@Composable
private fun PreviewHome() {
    HomeLayout(
        uiState = NoteUiState(
            notes = Note.getFakeNotes().take(3)
        )
    )
}