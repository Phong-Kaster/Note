package com.example.note.ui.fragment.compontent

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.core.LocalTheme
import com.example.note.R
import com.example.note.ui.theme.customizedTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier,
) {
    val fraction = scrollBehavior.state.collapsedFraction

    val alphaExpanded = animateFloatAsState(
        targetValue = if(fraction == 0.0f) 1.0f else 0.0f,
        animationSpec = tween(durationMillis = 100),
        label = "alphaExpanded"
    )
    
    val alphaCollapsed  = animateFloatAsState(
        targetValue = if (fraction == 1f) 1f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = "alphaCollapsed"
    )

    MediumTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.note),
                style = customizedTextStyle(
                    fontSize = 25,
                    fontWeight = 500,
                    color = LocalTheme.current.textColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 0.dp, vertical = 0.dp)
                    .alpha(alpha = alphaExpanded.value)
            )

            Text(
                text = stringResource(R.string.note),
                style = customizedTextStyle(
                    fontSize = 18,
                    fontWeight = 800,
                    color = LocalTheme.current.textColor
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp)
                    .alpha(alpha = alphaCollapsed.value)
            )
        },
        actions = {
        },
        navigationIcon = {

        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            scrolledContainerColor = LocalTheme.current.background,
            titleContentColor = LocalTheme.current.textColor,
            containerColor = LocalTheme.current.background
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun PreviewCollapsedTopbar() {
    TopBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState()),
        modifier = Modifier,
    )
}