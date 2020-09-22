package com.pratik.demoapp.data.model.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    // in-memory cache of the loggedInUser object
    var user: FirebaseUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = FirebaseAuth.getInstance().currentUser
    }

    fun logout() {
        user = null
        dataSource.logout()
    }

    fun login(username: String, password: String, callback: ResultCallback) {
        // handle login
        dataSource.login(username, password, object : ResultCallback {
            override fun onCallbackReceived(result: Result<FirebaseUser>) {
                callback.onCallbackReceived(result)
                if (result is Result.Success) {
                    setLoggedInUser(result.data)
                }
            }
        })
    }

    fun signUp(username: String, password: String, callback: ResultCallback) {
        dataSource.signUp(username, password, object : ResultCallback {
            override fun onCallbackReceived(result: Result<FirebaseUser>) {
                callback.onCallbackReceived(result)
                if (result is Result.Success) {
                    setLoggedInUser(result.data)
                }
            }
        })
    }

    private fun setLoggedInUser(loggedInUser: FirebaseUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}