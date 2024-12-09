package com.example.note.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.core.LocalTheme
import com.example.note.R
import com.example.note.ui.theme.customizedTextStyle

@Composable
fun TopBar(
    isCollapsed: Boolean,
    title: String = stringResource(R.string.note),
    modifier: Modifier = Modifier,
) {
    val colorTitle: Color by animateColorAsState(
        label = "colorTitle",
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (isCollapsed) LocalTheme.current.textColor else Color.Transparent,
    )

    val colorBackground: Color by animateColorAsState(
        label = "colorTitle",
        animationSpec = tween(durationMillis = 1000),
        targetValue = if (isCollapsed) Color.Transparent else Color.Transparent,
    )

    val corner by animateIntAsState(
        targetValue = if (isCollapsed) 10 else 0,
        animationSpec = tween(durationMillis = 1000),
        label = "corner"
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = corner.dp,
                    bottomEnd = corner.dp
                )
            )
            .background(color = colorBackground)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        Text(
            text = title,
            style = customizedTextStyle(
                fontSize = 24,
                fontWeight = 600,
                color = colorTitle
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
private fun PreviewTopBarCollapsed() {
    TopBar(
        isCollapsed = true,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
    )
}


@Preview
@Composable
private fun PreviewTopBarExtended() {
    TopBar(
        isCollapsed = false,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Blue)
    )
}