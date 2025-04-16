package com.G2.chatapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.G2.chatapp.networking.AuthManager

class LoginViewModel: ViewModel() {

    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var displayName by mutableStateOf("")
        private set
    var isAuthenticated by mutableStateOf<Boolean>(false)
    var loginResult by mutableStateOf<String>("")

    init {
        isAuthenticated = (AuthManager.id != null)
    }

    fun onEmailChange(email: String) {
        this.email = email
    }

    fun onPasswordChange(password: String) {
        this.password = password
    }

    fun onDisplayNameChange(displayName: String) {
        this.displayName = displayName
    }

    //firebase code와 연동하기
    fun login() {
        AuthManager.signIn(email, password) { success, message ->
            loginResult = if (success) "로그인 성공:$message " else "로그인 실패:$message"
            isAuthenticated = success
        }
    }

    //아이디와 패스워드가 올바른지 검증
    fun validateLoginInfo() {

    }

    //계정만들기
    fun createAccount() {
        AuthManager.createUser(email, password) { success, message ->
            loginResult = message.toString()
            isAuthenticated = success
        }
    }

    //로그아웃
    fun logout() {
        AuthManager.signOut()
    }
}