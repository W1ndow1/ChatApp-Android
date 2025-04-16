package com.G2.chatapp.networking

import com.google.firebase.auth.FirebaseAuth

object AuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val id: String?
        get() =  auth.currentUser?.uid.toString()

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
                    onResult(true, auth.currentUser?.email)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }
}