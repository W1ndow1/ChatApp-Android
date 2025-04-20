package com.G2.chatapp.ui.sub

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.G2.chatapp.viewModel.LoginViewModel
import com.G2.chatapp.viewModel.SettingViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    settingViewModel: SettingViewModel = SettingViewModel()
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.CenterStart,
            ) {
                Row {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                    Text(
                        text = "환경설정",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .padding(5.dp)
                            .align(Alignment.CenterVertically)
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
                //원하는 뷰 넣기
                SettingBody(navController ,viewModel)
            }
        }
    )
}
@Composable
fun SettingBody(
    navController: NavController,
    viewModel: LoginViewModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SettingItem(
                title = "로그아웃",
                onClick = {
                    viewModel.logout()
                    if (viewModel.isAuthenticated) {
                        navController.navigate("auth_graph") {
                            popUpTo("main_graph") { inclusive = true }
                        }
                    }
                }
            )
        }
    }
}
@Composable
fun SettingItem(
    title: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, style = MaterialTheme.typography.bodyLarge)

        }
    }
}