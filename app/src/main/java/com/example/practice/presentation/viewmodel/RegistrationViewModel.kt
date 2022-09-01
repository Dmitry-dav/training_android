package com.example.practice.presentation.viewmodel

import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.domain.model.RegistrationForm
import com.example.practice.domain.usecase.RegistrationUseCase
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase
): ViewModel() {

    val success: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun register(form: RegistrationForm) {
        val result = registrationUseCase.execute(form)

        if (result.flag) {
            success.value = true
        }
    }

}