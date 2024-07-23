package com.example.thefirst.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thefirst.R
import com.example.thefirst.core.models.MessageModel
import com.example.thefirst.core.models.messageData
import com.example.thefirst.core.utils.CURRENT_USER
import kotlinx.serialization.json.Json


@Composable
fun ConversationView(modifier: Modifier = Modifier, datas: List<MessageModel>) {
    LazyColumn(
        modifier = modifier,
        reverseLayout = true
    ) {
        itemsIndexed(datas) { index, item ->
            MessageItem(data = item, isLastItem = (index == 1))
            if (index < datas.lastIndex)
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .height(16.dp), color = Color.Transparent
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationViewPreview() {
    val messages = Json.decodeFromString<Array<MessageModel>>(messageData)
    ConversationView(modifier = Modifier, datas = messages.toList())
}

@Composable
fun MessageItem(modifier: Modifier = Modifier, data: MessageModel, isLastItem: Boolean = false) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (data.userId == CURRENT_USER)
                AsyncImage(
                    model = "https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg?w=996&t=st=1721621479~exp=1721622079~hmac=c16d695c313cb529961abc81b56513473d393cb86e6c3b7f2c91fef74f06b2d9",
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(shape = RoundedCornerShape(18.dp)),
                )
            else
                Image(
                    painterResource(R.drawable.icon_app),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(36.dp)
                        .clip(shape = RoundedCornerShape(18.dp)),
                )
            if (data.userId == CURRENT_USER && isLastItem)
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Request"
                    )
                }
            else if (data.userId != CURRENT_USER)
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Filled.VolumeUp,
                        contentDescription = "Text To Speech"
                    )
                }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(data.message)
        if (data.userId != CURRENT_USER)
            Row {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(R.drawable.dislike), "Dislike")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(painterResource(R.drawable.like), "Like")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Share, "Share")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Image(painterResource(R.drawable.google), "Google")
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.MoreVert, "More")
                }
            }
    }
}