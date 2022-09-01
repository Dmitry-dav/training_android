package com.example.practice.domain.usecase

import com.example.practice.domain.model.AuthorizationForm
import com.example.practice.domain.model.ResponseFromServer
import com.example.practice.domain.repository.UserRepository

class AuthorizationUseCase(private val userRepo: UserRepository) {

    fun execute(form: AuthorizationForm): ResponseFromServer {
        return userRepo.authorize(form)
    }
}