package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.chatapp.ui.login.LoginScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.ui.login.RegistScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var navController = rememberNavController()
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    LoginScreen(
                        modifier = Modifier,
                        navController = navController
                    )
                }
                composable("regist") {
                    RegistScreen()
                }
            }

        }
    }
}
