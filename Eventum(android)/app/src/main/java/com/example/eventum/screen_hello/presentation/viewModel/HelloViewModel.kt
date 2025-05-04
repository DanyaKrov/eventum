package com.example.eventum.screen_hello.presentation.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.eventum.data.local.preferences.UserPreferences
import com.example.eventum.screen_hello.presentation.event.HelloEvent
import com.example.eventum.screen_signUp.domain.model.SignUpModel
import com.example.eventum.screen_signUp.domain.useCase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import com.example.eventum.common.Constants
import com.example.eventum.util.reader.StringRepository

@HiltViewModel
class HelloViewModel @Inject constructor(
    private val stringRepository: StringRepository,
    private val signUpUseCase: SignUpUseCase,
    private val userPreferences: UserPreferences
): ViewModel(){
    private val navigationStatus: MutableStateFlow<String> = MutableStateFlow("")
    val navigationStatusRead: StateFlow<String> = navigationStatus
    private val _model = mutableStateOf(SignUpModel())
    val model: State<SignUpModel> = _model
    fun handleEvent(helloEvent: HelloEvent) {
        when (helloEvent) {
            is HelloEvent.MoveToLogin -> {
                navigationStatus.value = Constants.NAVIGATION_MOVE_TO_LOGIN_PAGE
            }
            is HelloEvent.MoveToSignUp -> {
                navigationStatus.value = Constants.NAVIGATION_MOVE_TO_SIGNUP_PAGE
            }
        }
    }
}