package com.example.note.ui.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jetpack.core.LocalNavController
import com.example.jetpack.core.LocalTheme

import com.example.note.domain.enums.Menu
import com.example.note.ui.modifier.borderWithAnimatedGradient
import com.example.note.ui.theme.customizedTextStyle

@Composable
fun CoreBottomBar() {
    // For navigating to other destinations
    val navController = LocalNavController.current ?: rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .borderWithAnimatedGradient(
                width = 2.dp,
                shape = RoundedCornerShape(0.dp),
                colors = listOf(
                    Color(0xFF004BDC),
                    Color(0xFF004BDC),
                    Color(0xFF9EFFFF),
                    Color(0xFF9EFFFF),
                    Color(0xFF9EFFFF),
                    Color(0xFF9EFFFF),
                    Color(0xFF004BDC),
                    Color(0xFF004BDC)
                ),
            )
            .background(color = LocalTheme.current.background)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        listOf(Menu.Note, Menu.Task).forEach { item ->
            BottomBarItem(
                selected = currentDestination?.hierarchy?.any { it.id == item.destinationId } == true,
                drawableId = item.drawableId,
                stringId = item.nameId,
                modifier = Modifier.weight(1f)
            ) {
                if (currentDestination?.id != item.homeDestinationId) {
                    navController.navigate(item.directions)
                }
            }
        }

        /*Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(48.dp)
                .clip(shape = CircleShape)
                .background(color = LocalTheme.current.secondary)

                .clickable { showBottomSheet = !showBottomSheet },
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color.White
            )
        }*/

        /*listOf(Menu.Note, Menu.Task).forEach { item ->
            BottomBarItem(
                selected = currentDestination?.hierarchy?.any { it.id == item.destinationId } == true,
                drawableId = item.drawableId,
                stringId = item.nameId,
                modifier = Modifier.weight(1f)
            ) {
                if (currentDestination?.id != item.homeDestinationId) {
                    navController.navigate(item.directions)
                }
            }
        }*/
    }


    /*HomeBottomSheet(
        enable = showBottomSheet,
        onDismissRequest = { showBottomSheet = false },
        openLanguage = {
            val destination = R.id.toLanguage
            navController.navigate(destination)
        },
        openDisclaimer = {
            val destination = R.id.toDisclaimer
            navController.navigate(destination)
        }
    )*/
}

@Composable
private fun BottomBarItem(
    selected: Boolean,
    @DrawableRes drawableId: Int,
    @StringRes stringId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = 35.dp),
                onClick = onClick
            )
            .padding(top = 4.dp, bottom = 12.dp),
    ) {
        Icon(
            painter = painterResource(drawableId),
            contentDescription = stringResource(id = stringId),
            modifier = Modifier.size(24.dp),
            tint = if (selected) LocalTheme.current.textColor else Color.LightGray,
        )

        if (selected) {
            Text(
                text = stringResource(stringId),
                style = customizedTextStyle(
                    fontSize = 14,
                    fontWeight = 600,
                ),
                color = if (selected) LocalTheme.current.textColor else LocalTheme.current.dim,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    CoreBottomBar()
}