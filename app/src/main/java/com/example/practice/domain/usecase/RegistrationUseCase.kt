package com.example.practice.domain.usecase

import com.example.practice.domain.model.AuthorizationForm
import com.example.practice.domain.model.RegistrationForm
import com.example.practice.domain.model.ResponseFromServer
import com.example.practice.domain.repository.UserRepository

class RegistrationUseCase(private val userRepo: UserRepository) {

    fun execute(form: RegistrationForm): ResponseFromServer {
        return userRepo.register(form)
    }
}