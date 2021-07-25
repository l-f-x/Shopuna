package com.lfx.shopuna.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.databinding.ActivityLoginBinding
import com.lfx.shopuna.ui.base.ViewModelFactory
import com.lfx.shopuna.ui.viewmodel.AuthViewModel
import com.lfx.shopuna.utils.NetworkDialogUtils
import com.lfx.shopuna.utils.NetworkStatus

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityLoginBinding

@SuppressLint("StaticFieldLeak")
private lateinit var dialogHelper: NetworkDialogUtils
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Shopuna)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.emailField.setText(bundle?.getString("email"))
        binding.passwordField.setText(bundle?.getString("pass"))
        dialogHelper = NetworkDialogUtils(this, layoutInflater)

    }

    fun login(view: View) {
        val authHelper = AuthHelper(RetrofitBuilder.authService)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(authHelper)).get(AuthViewModel::class.java)

        dialogHelper.showLoadingDialog("Авторизация")
        viewModel.login(binding.emailField.text.toString(), binding.passwordField.text.toString())
            .observe(this) {
                when (it.status) {
                    NetworkStatus.LOADING -> dialogHelper.showLoadingDialog("Авторизация")
                    NetworkStatus.SUCCESS -> dialogHelper.showSuccessDialog("Учпешная авторизация")
                    NetworkStatus.ERROR -> dialogHelper.showErrorDialog("Ошибка!")
                }
            }
    }

    fun goMainContent() {
        val intent = Intent(this, MainContentActivity::class.java)
        startActivity(intent)
    }
    
    fun goRegisterActivity(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        val args = Bundle()
        args.putString("email", binding.emailField.text.toString())
        args.putString("pass", binding.passwordField.text.toString())
        intent.putExtras(args)
        startActivity(intent)
    }

}