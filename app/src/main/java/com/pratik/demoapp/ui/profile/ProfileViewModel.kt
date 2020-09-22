package com.pratik.demoapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel : ViewModel() {

    private val _email = MutableLiveData<String>().apply {
        value = getCurrentUser()?.email ?: "NA"
    }
    val email: LiveData<String> = _email
    val isLoggedIn = MutableLiveData(getCurrentUser() != null)
    fun logout() {
        FirebaseAuth.getInstance().signOut()
        isLoggedIn.value = getCurrentUser() != null
    }

    private fun getCurrentUser(): FirebaseUser? {
        return FirebaseAuth.getInstance().currentUser
    }
}