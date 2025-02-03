package com.example.eventum.login.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.api.model.UserResponse
import com.example.eventum.login.api.LoginRepository
import com.example.eventum.login.event.LoginEvent
import com.example.eventum.login.model.LoginModel
import com.example.eventum.util.StringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val stringRepository: StringRepository,
    private val loginRepository: LoginRepository
): ViewModel() {
    private val model: LoginModel = LoginModel();

    fun handleEvent(loginEvent: LoginEvent) {
        when (loginEvent) {
            is LoginEvent.EmailChanged -> changeEmail(loginEvent.email)
            is LoginEvent.PasswordChanged -> changePassword(loginEvent.password)
            is LoginEvent.LoginFinished -> finishLogin()
            is LoginEvent.MoveToSignUp -> moveToSignUp()
        }
    }

    private fun changeEmail(email: String) {
        model.email = email
    }

    private fun changePassword(password: String) {
        model.password = password
    }

    private fun finishLogin() {
        viewModelScope.launch {
            val user: UserResponse = loginRepository.getUser(model.email)
            Log.e("check", "${user.email} ${user.id}")
        }
    }

    private fun moveToSignUp() {
    }
}