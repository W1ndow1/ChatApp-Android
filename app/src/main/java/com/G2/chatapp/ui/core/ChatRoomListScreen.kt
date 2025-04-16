package com.G2.chatapp.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ChatRoomListScreen(
    navController: NavController
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
                    text = "메시지",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(5.dp)

                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Settings"
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate("settings")
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }

                }
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                //원하는 뷰 넣기
            }
        }
    )
}