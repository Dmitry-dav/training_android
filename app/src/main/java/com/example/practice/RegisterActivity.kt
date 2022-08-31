package com.example.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practice.databinding.ActivityRegisterBinding
import android.annotation.SuppressLint
import android.content.Context
import android.util.Patterns
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat.getColorStateList
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.util.regex.Pattern

@SuppressLint("CheckResult")
class RegisterActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val nameStream = RxTextView.textChanges(binding.etName)
            .skipInitialValue()
            .map { name ->
                name.isEmpty()
            }
        nameStream.subscribe{
            showNameExistAlert(it)
        }

        val emailStream = RxTextView.textChanges(binding.etEmail2)
            .skipInitialValue()
            .map { email ->
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            }
        emailStream.subscribe {
            showEmailValidAlert( it)
        }

        val numberStream = RxTextView.textChanges(binding.etNumber)
            .skipInitialValue()
            .map { number ->
                number.length !== 11
            }
        numberStream.subscribe(){
            showTextMinimalAlert(it, "number")
        }

        val passwordStream = RxTextView.textChanges(binding.etPassword)
            .skipInitialValue()
            .map { password ->
                password.length < 8
            }
        passwordStream.subscribe(){
            showTextMinimalAlert(it, "password")
        }

        val invalidFieldsStream = Observable.combineLatest(
            nameStream,
            emailStream,
            numberStream,
            passwordStream,
            {nameInvalid: Boolean, emailInvalid: Boolean, numberInvalid: Boolean, passwordInvalid: Boolean ->
                !nameInvalid && !emailInvalid && !numberInvalid && !passwordInvalid
            })
        invalidFieldsStream.subscribe { isValid ->
            if (isValid) {
                binding.btnRegister.isEnabled = true
                binding.btnRegister.backgroundTintList = ContextCompat.getColorStateList(this, R.color.orange)
            } else {
                binding.btnRegister.isEnabled = false
                binding.btnRegister.backgroundTintList = ContextCompat.getColorStateList(this, R.color.grey)
            }
        }
        

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        binding.btnHaveAcc.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
     }
    
    private fun showNameExistAlert(isNotValid: Boolean) {
        binding.etName.error = if (isNotValid) "Имя не может быть пустым" else null
    }

    private fun showTextMinimalAlert(isNotValid: Boolean, text: String) {
        if (text == "number")
            binding.etNumber.error = if (isNotValid) "$text должно быть 11 цифр" else null
        else if (text =="password")
            binding.etPassword.error = if (isNotValid) "$text должно  быть более 8 цифр" else null
    }

    private fun showEmailValidAlert(isNotValid: Boolean) {
        binding.etEmail2.error = if (isNotValid) "Неверный адрес" else null
    }

}