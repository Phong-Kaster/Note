package com.example.note.ui.fragment.home

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.R
import com.example.note.domain.model.Note
import com.example.note.ui.component.CoreExpandableFloatingButton
import com.example.note.ui.fragment.home.compontent.NoteElement
import com.example.note.ui.component.TopBar
import com.example.note.ui.theme.customizedTextStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : CoreFragment() {

    private val viewModel: HomeViewModel by viewModels()

    @Composable
    override fun ComposeView() {
        super.ComposeView()
        HomeLayout(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        )
    }
}

@Composable
fun HomeLayout(
    uiState: HomeUiState
) {

    // for expandable floating action button
    val state = rememberLazyListState()
    val isCollapsed by remember { derivedStateOf { state.firstVisibleItemIndex > 0 } }


    CoreLayout(
        topBar = {
            TopBar(
                isCollapsed = isCollapsed,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        floatingActionButton = { CoreExpandableFloatingButton(extended = isCollapsed) },
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
                    key = { index: Int, item: Note -> "$index,${item.uid}" },
                    itemContent = { index, note ->
                        NoteElement(note = note)
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
        uiState = HomeUiState(
            notes = Note.getFakeNotes()
        )
    )
}