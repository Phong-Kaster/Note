package com.example.note.ui.fragment.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.note.ui.component.CoreBottomBar
import com.example.note.ui.component.CoreExpandableFloatingButton
import com.example.note.ui.component.TopBar
import com.example.note.ui.theme.customizedTextStyle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment : CoreFragment() {

    private val viewModel: TaskViewModel by viewModels()

    @Composable
    override fun ComposeView() {
        TaskLayout(
            uiState = viewModel.uiState.collectAsStateWithLifecycle().value
        )
    }
}

@Composable
fun TaskLayout(
    uiState: TaskUiState
) {

    // for expandable floating action button
    val state = rememberLazyListState()
    val isCollapsed by remember { derivedStateOf { state.firstVisibleItemIndex > 0 } }

    CoreLayout(
        topBar = {
            TopBar(
                title = stringResource(R.string.task),
                isCollapsed = isCollapsed,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
            )
        },
        bottomBar = { CoreBottomBar() },
        floatingActionButton = { CoreExpandableFloatingButton(extended = isCollapsed) },
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
                        text = stringResource(R.string.task),
                        style = customizedTextStyle(
                            fontSize = 32,
                            fontWeight = 800,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun PreviewTask() {
    TaskLayout(
        uiState = TaskUiState()
    )
}