package com.pratik.demoapp.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.pratik.demoapp.R
import com.pratik.demoapp.data.model.login.LoginRepository
import com.pratik.demoapp.data.model.login.Result
import com.pratik.demoapp.data.model.login.ResultCallback

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(username, password, object : ResultCallback {
            override fun onCallbackReceived(result: Result<FirebaseUser>) {
                if (result is Result.Success) {
                    _loginResult.value =
                        LoginResult(
                            success = LoggedInUserView(
                                displayName = result.data.email ?: "NA"
                            )
                        )
                } else if(result is Result.Error) {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        })
    }

    fun signUp(username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.signUp(username, password, object : ResultCallback {
            override fun onCallbackReceived(result: Result<FirebaseUser>) {
                if (result is Result.Success) {
                    _loginResult.value =
                        LoginResult(
                            success = LoggedInUserView(
                                displayName = result.data.email ?: "NA"
                            )
                        )
                } else {
                    _loginResult.value = LoginResult(error = R.string.sign_up_failed)
                }
            }
        })
    }

    fun isLoggedIn():Boolean {
        // can be launched in a separate asynchronous job
       return loginRepository.isLoggedIn
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        //   return if (username.contains('@')) {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
        /* } else {
             username.isNotBlank()
         }*/
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}