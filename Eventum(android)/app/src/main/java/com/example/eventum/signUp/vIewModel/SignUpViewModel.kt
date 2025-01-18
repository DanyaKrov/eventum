package com.example.eventum.signUp.vIewModel

import RetrofitClient
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.signUp.event.SignUpEvent
import com.example.eventum.signUp.model.SignUpModel
import com.example.eventum.signUp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel: ViewModel() {
    val signUpModel = MutableStateFlow(SignUpModel())

    init {
        viewModelScope.launch {
            try {
                val users = RetrofitClient.instance.getUsers()
                users.map {
                    Log.e("kolchak", it.email)
                }
            }
            catch (e: Exception) {
                Log.e("error", e.message.toString())
            }
        }
    }

    private fun updateEmail(email: String) {
        signUpModel.value.email = email
    }

    private fun updatePassword(password: String) {
        signUpModel.value.password = password
    }

    private fun finishRegistration() {

    }

    fun handleEvent(signUpEvent: SignUpEvent) {
        when (signUpEvent) {
            is SignUpEvent.EmailChanged -> {
                updateEmail(signUpEvent.emailAddress)
            }
            is SignUpEvent.PasswordChanged -> {
                updatePassword(signUpEvent.password)
            }
            is SignUpEvent.ButtonClicked -> {
                finishRegistration()
            }
        }
    }
}