package com.example.thefirst.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarCustom(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onTapMenu: (() -> Unit)? = null,
    onTapModeChat: (() -> Unit)? = null,
    onTapHistory: (() -> Unit)? = null,
    onTapProfile: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            TextButton(onClick = { onTapModeChat?.invoke() }) {
                Text(text = "Gemini")
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "Upgrade Package"
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onTapMenu?.invoke() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "App Drawer")
            }
        },
        actions = {
            IconButton(onClick = { onTapHistory?.invoke() }) {
                Icon(imageVector = Icons.Default.AccessTime, contentDescription = "History")
            }
            IconButton(onClick = { onTapProfile?.invoke() }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profile")
            }
        },
        scrollBehavior = scrollBehavior,
    )
}