package com.G2.chatapp.networking

import android.net.Uri
import com.google.firebase.Firebase
import com.google.firebase.storage.storage

object StorageManager {

    private val storageRef = Firebase.storage.reference

    fun uploadProfilePhoto(uid: String, image: Uri, onComplete:(Boolean, String?) -> Unit) {
        val ref = storageRef.child("images/users/${uid}.jpeg")
        val uploadTask = ref.putFile(image)

        uploadTask
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener { url ->
                    onComplete(true, url.toString())
                }
            }
            .addOnFailureListener { exception ->
                onComplete(false, exception.message)
            }

    }

}