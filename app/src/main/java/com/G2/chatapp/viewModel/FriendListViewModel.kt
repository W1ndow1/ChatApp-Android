package com.G2.chatapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.G2.chatapp.models.ChatUser
import com.G2.chatapp.networking.AuthManager
import com.G2.chatapp.networking.DatabaseManager

class FriendListViewModel: ViewModel() {

    var chatUsers by mutableStateOf(mutableListOf<ChatUser>())
        private set
    var favoriteUserIds by mutableStateOf<Set<String>?>(null)

    init {
        fetchChatUsers()
    }
    //등록된 사용자 가져오기
    fun fetchChatUsers() {
        DatabaseManager.collectionChatUsers { users ->
            chatUsers = users.toMutableList()
        }
    }
    suspend fun fetchFavorites() {
        val uid = AuthManager.id.toString()
        favoriteUserIds = DatabaseManager.collectionFavoriteUsers(uid) as Set<String>?
    }


}