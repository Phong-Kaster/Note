package com.example.note.ui.fragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.jetpack.core.CoreFragment
import com.example.jetpack.core.CoreLayout
import com.example.note.domain.model.Note
import com.example.note.ui.component.CoreExpandableFloatingButton
import com.example.note.ui.fragment.compontent.NoteElement
import com.example.note.ui.fragment.compontent.TopBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLayout(
    uiState: HomeUiState
) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    // for expandable floating action button
    val state = rememberLazyListState()
    val fabExtended by remember { derivedStateOf { state.firstVisibleItemIndex > 0 } }

    CoreLayout(
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            CoreExpandableFloatingButton(extended = fabExtended)
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        content = {
            LazyColumn(
                state = state,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
            ) {
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