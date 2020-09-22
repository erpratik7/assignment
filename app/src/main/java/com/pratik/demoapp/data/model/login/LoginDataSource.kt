package com.pratik.demoapp.data.model.login

import com.google.firebase.auth.FirebaseAuth
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private val auth = FirebaseAuth.getInstance()

    fun login(username: String, password: String, callback: ResultCallback) {
        try {
            auth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onCallbackReceived(Result.Success(auth.currentUser!!))
                    } else {
                        callback.onCallbackReceived(Result.Error(IOException("Error login in")))
                    }
                }
        } catch (e: Throwable) {
            e.printStackTrace()
            callback.onCallbackReceived(Result.Error(IOException(e.message)))
        }

    }

    fun signUp(username: String, password: String, callback: ResultCallback) {
        try {
            auth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        callback.onCallbackReceived(Result.Success(auth.currentUser!!))
                    } else {
                        callback.onCallbackReceived(Result.Error(IOException("Error login in")))
                    }
                }
        } catch (e: Throwable) {
            callback.onCallbackReceived(Result.Error(IOException(e.message)))
        }
    }

    fun logout() {
        auth.signOut()
    }
}