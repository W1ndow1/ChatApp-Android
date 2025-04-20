package com.G2.chatapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.G2.chatapp.models.ChatUser
import com.G2.chatapp.networking.AuthManager
import com.G2.chatapp.networking.DatabaseManager

class SettingViewModel {
    var currentUser by mutableStateOf<ChatUser?>(null)
    var isLogout by mutableStateOf<Boolean>(false)

    fun fetchCurrentUser() {
        val uid = AuthManager.id as String
        DatabaseManager.collectionChatUser(uid) { user ->
            currentUser = user
        }
    }

    fun handelSignOut() {
        AuthManager.signOut()
    }
}