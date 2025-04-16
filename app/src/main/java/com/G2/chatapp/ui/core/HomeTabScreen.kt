package com.G2.chatapp.ui.core

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.G2.chatapp.ui.login.LoginScreen
import com.G2.chatapp.ui.login.LoginViewModel
import com.G2.chatapp.ui.sub.SettingScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = LoginViewModel()
) {
    val navController = rememberNavController()
    var tabItems = listOf(
        BottomTabItem("친구", Icons.Default.Person, "friend_list"),
        BottomTabItem("메시지", Icons.Default.Email, "chat_list")
    )
    if(viewModel.isAuthenticated) {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier
                        .height(90.dp)
                ) {
                    var currentDestination = navController.currentBackStackEntryAsState().value?.destination
                    tabItems.forEach { item ->
                        var selected = currentDestination?.route == item.route
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
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
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
                navController = navController,
                startDestination = "friend_list",
                modifier = Modifier.padding(innerPadding),

                enterTransition = { slideInHorizontally(initialOffsetX = {1000}) + fadeIn() },
                exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}) + fadeOut() },
                popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}) + fadeIn()},
                popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}) + fadeOut()},

            ) {
                composable("friend_list") {
                    FriendListScreen(navController)
                }
                composable("chat_list") {
                    ChatRoomListScreen(navController)
                }
                composable("settings") {
                    SettingScreen(navController)
                }
            }
        }
    } else {
        LoginScreen(modifier, viewModel)
    }
}

data class BottomTabItem(
    var title: String,
    var icon: ImageVector,
    var route: String

)
