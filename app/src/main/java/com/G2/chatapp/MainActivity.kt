package com.G2.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.G2.chatapp.ui.core.HomeTabScreen
import com.G2.chatapp.ui.login.LoginScreen
import com.G2.chatapp.ui.regist.RegistScreen
import com.G2.chatapp.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = LoginViewModel()
            AppNavHost(viewModel)
        }
    }
}

@Composable
fun AppNavHost(
    viewModel: LoginViewModel,
    navController: NavHostController = rememberNavController()
) {
    var startDestination = if (viewModel.isAuthenticated) "main_graph" else "auth_graph"

    LaunchedEffect(viewModel.isAuthenticated) {
        navController.popBackStack(navController.graph.startDestinationId, inclusive = false)
        navController.navigate(startDestination)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        authGraph(navController, viewModel)
        mainGraph(navController, viewModel)
    }
}

//메인 네비게이트
fun NavGraphBuilder.mainGraph(
    navController: NavController, //=> 채팅방 이동시 사용할 예정
    viewModel: LoginViewModel
) {
    navigation(startDestination = "home", route = "main_graph") {
        composable("home") {
            HomeTabScreen(viewModel)
        }

    }
}

//로그인 네비게이트
fun NavGraphBuilder.authGraph(
    navController: NavController,
    viewModel: LoginViewModel
) {
    navigation(startDestination = "login", route = "auth_graph") {
        composable("login") {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate("main_graph") {
                        popUpTo("auth_graph") { inclusive = true }
                    }
                },
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("regist") {
            RegistScreen(navController, viewModel)
        }
    }
}
