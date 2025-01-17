package com.example.eventum.signUp.vIewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.signUp.api.SignUpRepository
import com.example.eventum.signUp.event.SignUpEvent
import com.example.eventum.signUp.model.SignUpModel
import com.example.eventum.signUp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SignUpViewModel: ViewModel() {
    private val signUpApi = RetrofitClient.instance.create(SignUpRepository::class.java)
    val signUpModel = MutableStateFlow(SignUpModel())
    var user: User = User(email = "", password = "")


    private fun updateEmail(email: String) {
        signUpModel.value.email = email
    }

    private fun updatePassword(password: String) {
        signUpModel.value.password = password
    }

    private fun finishRegistration() {
        user.email = signUpModel.value.email.toString()
        user.password = signUpModel.value.password.toString()
        viewModelScope.launch {
            try {
                signUpApi.createUser(user)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        Log.e("kolchak", "${signUpModel.value.email} ${signUpModel.value.password}")
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