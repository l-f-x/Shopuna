package com.lfx.shopuna.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.databinding.ActivityLoginBinding
import com.lfx.shopuna.ui.base.ViewModelFactory
import com.lfx.shopuna.ui.viewmodel.AuthViewModel

lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Shopuna)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun login(view: View) {
        val authHelper = AuthHelper(RetrofitBuilder.authService)
        val  viewModel = ViewModelProvider(this, ViewModelFactory(authHelper)).get(AuthViewModel::class.java)
        viewModel.token.observe(this,{
            Toast.makeText(applicationContext, it.token, Toast.LENGTH_LONG).show()
        })
        viewModel.errorMessage.observe(this,{
            Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
        })
        viewModel.loading.observe(this,{
            Toast.makeText(applicationContext, it.toString(), Toast.LENGTH_LONG).show()
        })
        viewModel.login(binding.emailField.text.toString(), binding.passwordField.text.toString())
    }
    fun goRegisterActivity(view: View) {}
}