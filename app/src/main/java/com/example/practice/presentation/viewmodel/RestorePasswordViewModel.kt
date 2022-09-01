package com.example.practice.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.domain.model.RestorePasswordForm
import com.example.practice.domain.usecase.RestorePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor(
    private val restorePasswordUseCase: RestorePasswordUseCase
): ViewModel() {

    val success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun restorePassword(form: RestorePasswordForm) {
        val result = restorePasswordUseCase.execute(form)

        if (result.flag) {
            success.value = true
        }
    }
}