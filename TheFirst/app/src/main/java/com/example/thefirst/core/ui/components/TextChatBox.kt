package com.example.thefirst.core.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.thefirst.R

@Composable
fun TextChatBox(
    textFieldValue: TextFieldValue,
    onTextChanged: (TextFieldValue) -> Unit,
    keyboardType: KeyboardType,
    onMessageSent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textFieldValue,
        onValueChange = onTextChanged,
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.dp, color = Color.Transparent),
        trailingIcon = {
            Row {
                IconButton(onClick = {
                    /*TODO*/
                }) {
                    Icon(
                        imageVector = Icons.Default.ImageSearch,
                        contentDescription = "Search with image...",
                    )
                }
                IconButton(onClick = {
                    /*TODO*/
                }) {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Search with voice...",
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = ImeAction.Send
        ),
        keyboardActions = KeyboardActions {
            if (textFieldValue.text.isNotBlank()) onMessageSent(textFieldValue.text)
        },
        colors = TextFieldDefaults.colors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(stringResource(R.string.textfield_hint)) },
        shape = RoundedCornerShape(size = 28.dp),
    )
}