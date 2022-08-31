package com.example.practice

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.practice.databinding.ActivityLoginBinding
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable

@SuppressLint("CheckResult")
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emailStream = RxTextView.textChanges(binding.etEmail)
            .skipInitialValue()
            .map { email ->
                email.isEmpty()
            }
        emailStream.subscribe {
            showTextMinimalAlert(it, "email")
        }

        val passwordStream = RxTextView.textChanges(binding.etPassword)
            .skipInitialValue()
            .map { password ->
                password.isEmpty()
            }
        passwordStream.subscribe(){
            showTextMinimalAlert(it, "password")
        }

        val invalidFieldsStream = Observable.combineLatest(
            emailStream,
            passwordStream,
            {emailInvalid: Boolean, passwordInvalid: Boolean ->
                !emailInvalid && !passwordInvalid
            })
        invalidFieldsStream.subscribe { isValid ->
            if (isValid) {
                binding.btnLogin.isEnabled = true
                binding.btnLogin.backgroundTintList = ContextCompat.getColorStateList(this, R.color.orange)
            } else {
                binding.btnLogin.isEnabled = false
                binding.btnLogin.backgroundTintList = ContextCompat.getColorStateList(this, R.color.grey)
            }
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnForget.setOnClickListener {
            startActivity(Intent(this, RecoveryActivity::class.java))
        }
    }

        private fun showTextMinimalAlert(isNotValid: Boolean, text: String) {
            if (text == "email")
                binding.etEmail.error = if (isNotValid) "$text поле не может быть  пустым" else null
            else if (text =="password")
                binding.etPassword.error = if (isNotValid) "$text поле не может быть пустым" else null
        }
}