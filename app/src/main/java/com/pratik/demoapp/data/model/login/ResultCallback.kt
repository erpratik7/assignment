package com.pratik.demoapp.data.model.login

import com.google.firebase.auth.FirebaseUser

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
interface ResultCallback {
    fun onCallbackReceived(result: Result<FirebaseUser>)
}