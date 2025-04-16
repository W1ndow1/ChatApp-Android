package com.G2.chatapp.ui.regist

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.G2.chatapp.ui.login.LoginViewModel

@Composable
fun RegistScreen(
    viewModel: LoginViewModel = viewModel(LocalContext.current as ComponentActivity)
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(all = 20.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "회원가입",
                fontSize = 20.sp,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(10.dp))
            RegistHeaderSection()
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.onEmailChange(it) },
                label = { Text("이메일") },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                label = { Text("암호") },
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = viewModel.displayName,
                onValueChange = { viewModel.onDisplayNameChange(it) },
                label = { Text("이름") },
                maxLines = 1,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = { println("Login Button Tap") },
            ) { Text(
                "로그인",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            }
        }

    }
}

@Preview
@Composable
fun RegistScreenPreview() {
    RegistScreen()
}