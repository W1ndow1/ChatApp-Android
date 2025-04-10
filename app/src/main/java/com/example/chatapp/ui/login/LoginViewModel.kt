package com.example.chatapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set

    var emailError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    fun onEmailChange(email: String) {
        this.email = email
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    //firebase code와 연동하기
    fun login() {
    }

    //아이디와 패스워드가 올바른지 검증
    fun validateLoginInfo() {


    }
}