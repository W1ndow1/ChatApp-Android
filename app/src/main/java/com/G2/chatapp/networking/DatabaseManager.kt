package com.G2.chatapp.networking

import android.R
import com.G2.chatapp.models.ChatUser
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object DatabaseManager {

    private val db = Firebase.firestore

    fun collectionChatUsers(onResult: (List<ChatUser>) -> Unit) {
        db.collection("users").get()
            .addOnSuccessListener { snapshot ->
                if(snapshot != null && !snapshot.isEmpty) {
                    onResult(snapshot.toObjects(ChatUser::class.java))
                }
            }
            .addOnFailureListener { error ->
                println("Firestore Error: $error")
                onResult(emptyList())
            }
    }

    fun collectionChatUser(uid: String, onResult: (ChatUser?) -> Unit) {
        db.collection("users").document(uid).get()
            .addOnSuccessListener { snapshot ->
                if(snapshot.exists()) {
                    val user = snapshot.toObject(ChatUser::class.java)
                    onResult(user)
                }
            }
            .addOnFailureListener { error ->
                println("Firestore Error: $error")
                onResult(null)
            }
    }

    suspend fun collectionFavoriteUsers(uid: String): List<String> {
        val snapshot = db.collection("favorites").document(uid).get().await()
        return snapshot.get("favoriteChatRooms") as? List<String> ?: emptyList()
    }

    fun storeUserInfo(user: ChatUser, onResult: (Boolean, String?) -> Unit) {
        db.collection("users").document(user.uid)
            .set(user)
            .addOnSuccessListener {
                onResult(true, "등록성공")

            }
            .addOnFailureListener { error ->
                onResult(false, error.message)
            }
    }
}