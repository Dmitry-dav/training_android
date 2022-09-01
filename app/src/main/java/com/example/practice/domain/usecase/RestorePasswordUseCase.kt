package com.example.practice.domain.usecase

import com.example.practice.domain.model.ResponseFromServer
import com.example.practice.domain.model.RestorePasswordForm
import com.example.practice.domain.repository.UserRepository

class RestorePasswordUseCase(private val userRepo: UserRepository) {

    fun execute(form: RestorePasswordForm): ResponseFromServer {
        return userRepo.restorePassword(form)
    }
}