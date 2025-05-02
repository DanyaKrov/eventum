package com.example.eventum.screen_signUp.domain.useCase

import com.example.eventum.data.remote.model.response.UserRemote
import com.example.eventum.domain.model.User
import com.example.eventum.screen_signUp.domain.model.SignUpModel
import com.example.eventum.screen_signUp.domain.model.SignUpRequest
import com.example.eventum.screen_signUp.domain.repository.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) {
    suspend operator fun invoke(model: SignUpModel): User =
        signUpRepository.signUp(SignUpRequest(model.name, model.email, model.password ?: ""))
}