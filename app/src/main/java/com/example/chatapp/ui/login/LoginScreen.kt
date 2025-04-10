package com.example.chatapp.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    navController: NavController
) {
    Surface {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 30.dp)
                .padding(horizontal = 20.dp)
        ) {
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("이메일") },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(5.dp))

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("비밀번호") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = { println("Login Button Tap") },
                ) { Text("로그인", fontSize = 18.sp, fontWeight = FontWeight.Bold) }

            Spacer(modifier = Modifier.padding(20.dp))

            Text(
                text = "회원가입을 하시겠습니까?",
                modifier = modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        //Navigated RegistScreen
                        navController.navigate("regist")
                    }
            )
        }
    }
}

@Preview
@Composable
fun LoginScrennPreview() {
    LoginScreen(
        modifier = Modifier,
        navController = rememberNavController()
    )
}