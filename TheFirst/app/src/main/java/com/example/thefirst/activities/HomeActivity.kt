package com.example.thefirst.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.waterfall
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thefirst.R
import com.example.thefirst.core.models.MessageModel
import com.example.thefirst.core.models.messageData
import com.example.thefirst.core.ui.components.ConversationView
import com.example.thefirst.core.ui.components.TextChatBox
import com.example.thefirst.core.ui.components.TopAppBarCustom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                DrawerApp(content = { scope, drawerState ->
                    MainApp(onTapMenu = {
                        scope.launch {
                            drawerState.apply { open() }
                        }
                    })
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerApp(
    modifier: Modifier = Modifier, content: @Composable (CoroutineScope, DrawerState) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(Modifier.padding(10.dp)) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(53.dp)
                    ) {
                        IconButton(onClick = {
                            scope.launch { drawerState.apply { close() } }
                        }) {
                            Icon(Icons.Default.Menu, "Menu")
                        }
                    }
                    Column(Modifier.padding(12.dp)) {
                        Spacer(Modifier.height(10.dp))
                        ExtendedFloatingActionButton(
                            onClick = {},
                            icon = { Icon(Icons.Default.Add, "New Chat") },
                            text = { Text("New Chat") }
                        )
                        Spacer(Modifier.height(24.dp))
                        Box(Modifier.weight(1f)) {
                            LazyColumn {
                                item {
                                    Text(
                                        modifier = Modifier.padding(start = 12.dp),
                                        text = stringResource(R.string.recent),
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                                itemsIndexed(
                                    (1..5).toList(),
                                    itemContent = { index, item ->
                                        NavigationDrawerItem(
                                            icon = {
                                                Icon(
                                                    Icons.Default.ChatBubbleOutline,
                                                    "Chat"
                                                )
                                            },
                                            label = { Text("Chat $item") },
                                            selected = index == 2,
                                            onClick = {
                                                scope.launch {
                                                    drawerState.apply { close() }
                                                }
                                            }
                                        )
                                    }
                                )
                            }
                        }
                        Column {
                            Column {
                                NavigationDrawerItem(
                                    label = { Text("Hỗ trợ") },
                                    icon = {
                                        Icon(
                                            Icons.AutoMirrored.Outlined.Help,
                                            "Help"
                                        )
                                    },
                                    selected = false,
                                    onClick = {}
                                )
                                NavigationDrawerItem(
                                    label = { Text("Hoạt động") },
                                    icon = { Icon(Icons.Default.History, "History") },
                                    selected = false,
                                    onClick = {}
                                )
                                NavigationDrawerItem(
                                    label = { Text("Cài đặt") },
                                    icon = { Icon(Icons.Default.Settings, "Settings") },
                                    selected = false,
                                    onClick = {}
                                )
                            }
                            FilterChip(
                                selected = true,
                                onClick = {},
                                label = { Text("Dùng thử Gemini Advanced") },
                                leadingIcon = {
                                    Icon(
                                        Icons.Default.Star,
                                        "Upgrade to Gemini Advanced",
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            )
                        }
                    }
                }
            }
        },
        content = { content(scope, drawerState) },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainApp(modifier: Modifier = Modifier, onTapMenu: (() -> Unit)? = null) {
    val topBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(topBarState)

    Scaffold(
        topBar = {
            TopAppBarCustom(
                modifier = Modifier.fillMaxWidth(),
                scrollBehavior = scrollBehavior,
                onTapMenu = onTapMenu,
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp)
                    .fillMaxSize()
            ) {
                val messages = Json.decodeFromString<Array<MessageModel>>(messageData)
                messages.sortedBy { it.created }
                ConversationView(modifier = Modifier.weight(1f), datas = messages.toList())
                ChatField()
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.navigationBars)
            .exclude(WindowInsets.waterfall).exclude(WindowInsets.ime),
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    )
}

@Composable
fun ChatField() {
    var textState by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            TextChatBox(
                textFieldValue = textState,
                onTextChanged = { textState = it },
                onMessageSent = {
                    textState = TextFieldValue()
                },
                keyboardType = KeyboardType.Text,
            )
            PrivacyNotice()
        }
    }
}

@Composable
fun PrivacyNotice() {
    val context = LocalContext.current
    val annotatedString = remember {
        buildAnnotatedString {
            append("Gemini có thể đưa ra thông tin không chính xác, kể cả thông tin về con người, vì vậy, hãy xác minh các câu trả lời của Gemini. ")

            val start = length
            append("\nQuyền riêng tư của bạn và Các ứng dụng Gemini")
            addStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                ), start = start, end = length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = "https://support.google.com/gemini/answer/13594961?visit_id=638568858208701595-1970695162&p=privacy_notice&rd=1#privacy_notice",
                start = start,
                end = length
            )
        }
    }
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(annotation.item))
                    context.startActivity(intent)
                }
        },
        style = TextStyle(
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .padding(vertical = 16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}