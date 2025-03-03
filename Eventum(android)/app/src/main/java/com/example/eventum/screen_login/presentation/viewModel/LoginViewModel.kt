package com.example.eventum.screen_login.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventum.R
import com.example.eventum.screen_login.domain.model.AuthRequest
import com.example.eventum.screen_login.presentation.event.LoginEvent
import com.example.eventum.screen_login.domain.model.LoginModel
import com.example.eventum.screen_login.domain.useCase.LoginUseCase
import com.example.eventum.util.StringRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val stringRepository: StringRepository,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    // navigation parameters
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus

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
            if (checkRequirements()) {
                val authRequest = AuthRequest(model.email, model.password)
                try {
                    loginUseCase.execute(authRequest)
                    navigationStatus.value = "logged_in"
                }
                catch (e: Exception) {
                    model.response.value = "Email or password is incorrect"
                }
            }
        }
    }

    private fun moveToSignUp() {
    }

    private fun checkRequirements(): Boolean { // check requirements and change response in model if need
        model.response.value = ""
        if (model.email.isEmpty())
            model.response.value +=
                stringRepository.getString(R.string.EmailRequirement) + "\n"
        if (model.password.isEmpty())
            model.response.value +=
                stringRepository.getString(R.string.PasswordNotBlank) + "\n"
        else
            return true
        return false
    }
}