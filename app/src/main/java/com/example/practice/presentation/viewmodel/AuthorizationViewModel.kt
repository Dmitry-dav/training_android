package com.example.practice.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.domain.model.AuthorizationForm
import com.example.practice.domain.usecase.AuthorizationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val authorizationUseCase: AuthorizationUseCase
): ViewModel() {

    val success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun authorize(form: AuthorizationForm) {
        val result = authorizationUseCase.execute(form)

        if (result.flag) {
            success.value = true
        }
    }
}