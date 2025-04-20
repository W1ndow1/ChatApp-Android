package com.G2.chatapp.viewModel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.G2.chatapp.models.ChatUser
import com.G2.chatapp.networking.AuthManager
import com.G2.chatapp.networking.DatabaseManager
import com.G2.chatapp.networking.StorageManager

class LoginViewModel: ViewModel() {

    var email by mutableStateOf("")
    var password: String by mutableStateOf("")
    var displayName by mutableStateOf("")
    var isAuthenticated by mutableStateOf<Boolean>(false)
    var loginResult by mutableStateOf<String>("")
    var registResult by mutableStateOf<String>("")
    var image by mutableStateOf<Uri?>(null)


    init {
        isAuthenticated = (AuthManager.id != null)
    }

    fun login() {
        if (validateLoginInfo()) {
            AuthManager.signIn(email, password) { success, message ->
                loginResult = if (success) "로그인 성공:$message " else "로그인 실패:$message"
                isAuthenticated = success
            }
        } else {
            loginResult = "입력정보를 확인해주세요"
        }
    }
    fun createAccount() {
        if (validateRegistInfo()) {
            AuthManager.createUser(email, password) { success, message ->
                if(success) {
                    registResult = message.toString()
                    uploadImage(message.toString())
                } else {
                    registResult = "Firebase error: ${message}"
                }
            }
        } else {
            registResult = "입력정보를 확인해주세요"
        }
    }

    fun uploadImage(uid: String) {
        val seletedImage = image ?: return
        StorageManager.uploadProfilePhoto(uid, seletedImage) { success, message ->
            if (success) {
                //사용자 정보 다시 만들기
                storeUserInfo(message.toString(), uid)
            } else {
                registResult = "사진업로드를 실패 했습니다. :$message"
            }
        }
    }

    //업로드된 사용자 정보 올리기
    fun storeUserInfo(url: String, uid: String) {
        val userData = ChatUser(
            uid = uid,
            email = this.email,
            profileImageURL = url,
            displayName = this.displayName
        )
        DatabaseManager.storeUserInfo(userData) { success, message ->
            if (success) {
                registResult = "회원가입 성공"
                isAuthenticated = true
            } else {
                registResult = "회원가입 실패 : $message"
            }
        }
    }

    fun logout() {
        AuthManager.signOut()
        isAuthenticated = false
    }

    private fun validateLoginInfo(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun validateRegistInfo(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty() && displayName.isNotEmpty()
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
}
