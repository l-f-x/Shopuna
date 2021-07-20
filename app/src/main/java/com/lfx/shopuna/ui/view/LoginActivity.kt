package com.lfx.shopuna.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.databinding.ActivityLoginBinding
import com.lfx.shopuna.ui.base.ViewModelFactory
import com.lfx.shopuna.ui.viewmodel.AuthViewModel

private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Shopuna)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        if (bundle!=null) {
            binding.emailField.setText(bundle.getString("email", ""))
            binding.passwordField.setText(bundle.getString("pass", ""))
        }
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
    fun goRegisterActivity(view: View) {
        val intent = Intent(this,RegistrationActivity::class.java)
        val args = Bundle()
        args.putString("email", binding.emailField.text.toString())
        args.putString("pass", binding.passwordField.text.toString())
        intent.putExtras(args)
        startActivity(intent)
    }
}