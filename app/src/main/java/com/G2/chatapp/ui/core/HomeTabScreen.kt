package com.G2.chatapp.ui.core

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.G2.chatapp.ui.sub.SettingScreen
import com.G2.chatapp.viewModel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabScreen(
    viewModel: LoginViewModel
) {
    val tabItems = listOf(
        BottomTabItem("친구", Icons.Default.Person, "friend_list"),
        BottomTabItem("메시지", Icons.Default.Email, "chat_list")
    )
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier
                    .height(90.dp)
            ) {
                var currentDestination =
                    innerNavController.currentBackStackEntryAsState().value?.destination
                tabItems.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.offset(y = 5.dp)
                            )
                        },
                        label = {
                            Text(item.title, fontSize = 13.sp)
                        },
                        selected = currentDestination?.route == item.route,
                        onClick = {
                            innerNavController.navigate(item.route) {
                                popUpTo(innerNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = innerNavController,
            startDestination = "friend_list",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("friend_list") {
                FriendListScreen(innerNavController)
            }
            composable("chat_list") {
                ChatRoomListScreen(innerNavController)
            }
            composable("settings") {
                SettingScreen(innerNavController, viewModel)
            }
        }
    }
}

data class BottomTabItem(
    var title: String,
    var icon: ImageVector,
    var route: String

)