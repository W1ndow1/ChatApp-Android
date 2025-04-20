package com.G2.chatapp.ui.regist

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.G2.chatapp.viewModel.LoginViewModel
import kotlin.math.sin

@Composable
fun RegistScreen(
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
                Row {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로가기")
                    }
                    Text(
                        text = "가입하기",
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
                RegistScreenBody(navController ,viewModel)
            }
        }
    )
}

@Composable
fun RegistScreenBody(
    navController: NavController,
    viewModel: LoginViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(all = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val (emailFocus, passwordFocus, displayNameFocus) = remember { FocusRequester.createRefs() }
                val keyboardController = LocalSoftwareKeyboardController.current

                RegistHeaderSection(viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("이메일") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailFocus),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { passwordFocus.requestFocus() })

                )

                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("암호") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordFocus),
                    keyboardActions = KeyboardActions(onNext = { displayNameFocus.requestFocus() })
                )

                OutlinedTextField(
                    value = viewModel.displayName,
                    onValueChange = { viewModel.onDisplayNameChange(it) },
                    label = { Text("이름") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(displayNameFocus),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        viewModel.createAccount()
                        keyboardController?.hide()
                    },
                ) {
                    Text(
                        text = "가입하기",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    viewModel.registResult,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }
    }
}