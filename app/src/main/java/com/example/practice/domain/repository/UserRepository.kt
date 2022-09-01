package com.example.practice.domain.repository

import com.example.practice.domain.model.AuthorizationForm
import com.example.practice.domain.model.RegistrationForm
import com.example.practice.domain.model.ResponseFromServer
import com.example.practice.domain.model.RestorePasswordForm

interface UserRepository {

    fun register(form: RegistrationForm): ResponseFromServer

    fun authorize(form: AuthorizationForm): ResponseFromServer

    fun restorePassword(form: RestorePasswordForm): ResponseFromServer
}