package com.example.practice.data

import com.example.practice.domain.model.AuthorizationForm
import com.example.practice.domain.model.RegistrationForm
import com.example.practice.domain.model.ResponseFromServer
import com.example.practice.domain.model.RestorePasswordForm
import com.example.practice.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    override fun register(form: RegistrationForm): ResponseFromServer {
        // запрос на сервер
        return ResponseFromServer(flag = true)
    }

    override fun authorize(form: AuthorizationForm): ResponseFromServer {
        // запрос на сервер
        return ResponseFromServer(flag = true)
    }

    override fun restorePassword(form: RestorePasswordForm): ResponseFromServer {
        // запрос на сервер
        return ResponseFromServer(flag = true)
    }
}