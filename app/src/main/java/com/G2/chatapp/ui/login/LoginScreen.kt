package com.G2.chatapp.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.G2.chatapp.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    navController: NavController,
    viewModel: LoginViewModel
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(50.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer
                    ),
                contentAlignment = Alignment.CenterStart,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "접속하기",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
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
                LoginBody(
                    onLoginSuccess,
                    navController,
                    viewModel
                )
            }
        }
    )
}

@Composable
fun LoginBody(
    onLoginSuccess: () -> Unit,
    navController: NavController,
    viewModel: LoginViewModel
) {
    Surface {
        Column(
            modifier = Modifier
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
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    viewModel.login()
                    if (viewModel.isAuthenticated) { onLoginSuccess() }
                }
            ) {
                Text("로그인", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                viewModel.loginResult,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "회원가입을 하시겠습니까?",
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .clickable {
                        //Navigated RegistScreen
                        navController.navigate("regist")
                    }
            )
        }
    }
}