package com.G2.chatapp.ui.core

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.G2.chatapp.models.ChatUser
import com.G2.chatapp.networking.AuthManager
import com.G2.chatapp.viewModel.FriendListViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendListScreen(
    navController: NavController,
    viewModel: FriendListViewModel = FriendListViewModel()
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        //shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "친구",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(5.dp)

                )
                IconButton(
                    onClick = { navController.navigate("settings")},
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                FriendList(viewModel)
            }
        }
    )
}

@Composable
fun FriendList(viewModel: FriendListViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            //.padding(top = 30.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true
    ) {
        val currentUser = viewModel.chatUsers.find { it.uid == AuthManager.id }
        val otherUser = viewModel.chatUsers.filter { it.uid != AuthManager.id }

        currentUser?.let {
            item {
                UserRow(it)
                Spacer(Modifier.size(10.dp))
                HorizontalDivider(thickness = 0.5.dp)

            }
        }
        item {
            Text(
                text = "친구 ${otherUser.count()}",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
        items(otherUser) { user ->
            UserRow(user)

        }
    }
}

@Composable
fun UserRow(user: ChatUser) {
    Row(
        modifier = Modifier
            .padding(start = 15.dp)
            .padding(top = 5.dp)
    ) {
        AsyncImage(
            model = user.profileImageURL,
            contentDescription = "프로필 이미지",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(50.dp)
                .border(
                    BorderStroke(1.dp, Color.DarkGray),
                    CircleShape
                )
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier
            .size(5.dp))
        Column(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
        ) {
            Text(
                modifier = Modifier,
                text = user.displayName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier,
                text = user.email,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}
