package com.example.eventum.screen_signUp.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.R
import com.example.eventum.common.Constants
import com.example.eventum.screen_signUp.presentation.event.SignUpEvent
import com.example.eventum.screen_signUp.domain.model.SignUpModel
import com.example.eventum.screen_signUp.domain.useCase.SignUpUseCase
import com.example.eventum.util.StringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val stringRepository: StringRepository, // repository for reading string
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus
    private val _model = mutableStateOf(SignUpModel())
    val model = _model

    private fun updateEmail(email: String) {
        _model.value.email = email
    }

    private fun updatePassword(password: String) {
        _model.value.password = password
    }

    private fun updateSecondPassword(password: String) {
        _model.value.secondPassword = password
    }

    private fun finishRegistration() {
        viewModelScope.launch {
            if (checkRequirements()) {
                try {
                    // useCase of checking if email is already used in the system
                    signUpUseCase(model.value)
                    navigationStatus.value = Constants.NAVIGATION_MOVE_TO_MAIN_PAGE
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
                navigationStatus.value = Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE
            }
        }
    }


    private fun checkRequirements(): Boolean { // check requirements and change signUpModel if need
        model.value.requirementsStatement.value = ""
        if (!model.value.email.contains("@"))
            model.value.requirementsStatement.value +=
                stringRepository.getString(R.string.EmailRequirement) + "\n"
        if (!model.value.password.equals(model.value.secondPassword))
            model.value.requirementsStatement.value +=
                stringRepository.getString(R.string.PasswordsIdentity) + "\n"
        if ((model.value.password?.length ?: 0) < 8)
            model.value.requirementsStatement.value +=
                stringRepository.getString(R.string.PasswordLength) + "\n"
        else
            return true
        return false
    }
}