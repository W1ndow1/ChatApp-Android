package com.G2.chatapp.networking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.firebase.auth.FirebaseAuth

object AuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var id =  auth.currentUser?.uid
        private set

    fun signIn(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, auth.currentUser?.email)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun createUser(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    id = auth.currentUser?.uid
                    onResult(true, auth.currentUser?.uid)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }
}