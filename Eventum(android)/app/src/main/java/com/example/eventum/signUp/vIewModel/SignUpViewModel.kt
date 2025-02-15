package com.example.eventum.signUp.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.R
import com.example.eventum.signUp.event.SignUpEvent
import com.example.eventum.signUp.model.SignUpModel
import com.example.eventum.api.model.UserRequest
import com.example.eventum.signUp.api.SignUpRepository
import com.example.eventum.util.StringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val stringRepository: StringRepository, // repository for reading string
    private val repository: SignUpRepository // repository for working with API
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus
    val signUpModel: SignUpModel = SignUpModel()

    private fun updateEmail(email: String) {
        signUpModel.email = email
    }

    private fun updatePassword(password: String) {
        signUpModel.password = password
    }

    private fun updateSecondPassword(password: String) {
        signUpModel.secondPassword = password
    }

    private fun finishRegistration() {
        viewModelScope.launch {
            val user = UserRequest(name = signUpModel.name.toString(),
                email = signUpModel.email)
            if (checkRequirements()) {
                try {
                    repository.createUser(user)
                }
                catch (e: Exception) {
                    Log.e("testing", e.message.toString())
                }
            }
        }
    }

    fun handleEvent(signUpEvent: SignUpEvent) {
        when (signUpEvent) {
            is SignUpEvent.EmailChanged -> {
                updateEmail(signUpEvent.emailAddress)
            }
            is SignUpEvent.PasswordChanged -> {
                updatePassword(signUpEvent.password)
            }
            is SignUpEvent.SecondPasswordChanged -> {
                updateSecondPassword(signUpEvent.password)
            }
            is SignUpEvent.SignUpFinished -> {
                finishRegistration()
            }
            is SignUpEvent.MoveToLogin -> {
                navigationStatus.value = "move_to_login"
            }
        }
    }


    private fun checkRequirements(): Boolean { // check requirements and change signUpModel if need
        signUpModel.requirementsStatement.value = ""
        if (!signUpModel.email.contains("@"))
            signUpModel.requirementsStatement.value +=
                stringRepository.getString(R.string.EmailRequirement) + "\n"
        if (!signUpModel.password.equals(signUpModel.secondPassword))
            signUpModel.requirementsStatement.value +=
                stringRepository.getString(R.string.PasswordsIdentity) + "\n"
        if ((signUpModel.password?.length ?: 0) < 8)
            signUpModel.requirementsStatement.value +=
                stringRepository.getString(R.string.PasswordLength) + "\n"
        else
            return true
        return false
    }
}