package com.lfx.shopuna.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.databinding.ActivityLoginBinding
import com.lfx.shopuna.ui.base.AuthViewModelFactory
import com.lfx.shopuna.ui.viewmodel.AuthViewModel
import com.lfx.shopuna.utils.NetworkDialogUtils
import com.lfx.shopuna.utils.NetworkStatus
import com.lfx.shopuna.utils.TOKEN
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityLoginBinding

@SuppressLint("StaticFieldLeak")
private lateinit var dialogHelper: NetworkDialogUtils
private lateinit var prefs: SharedPreferences
private lateinit var viewModel: AuthViewModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        dialogHelper = NetworkDialogUtils(this, layoutInflater)
        prefs = getSharedPreferences(USER_DATA_FILE_NAME, MODE_PRIVATE)
        checkUserAuth(prefs)
        val authHelper = AuthHelper(RetrofitBuilder.authService)
        viewModel =
            ViewModelProvider(this, AuthViewModelFactory(authHelper)).get(AuthViewModel::class.java)
        setTheme(R.style.Theme_Shopuna)
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val bundle = intent.extras
        binding.emailField.setText(bundle?.getString("email"))
        binding.passwordField.setText(bundle?.getString("pass"))
        setContentView(binding.root)

    }

    fun login(view: View) {
        dialogHelper.showLoadingDialog("Авторизация")
        viewModel.login(binding.emailField.text.toString(), binding.passwordField.text.toString())
            .observe(this) {
                Log.d("TAG", "register: ${it.status}")
                when (it.status) {
                    NetworkStatus.LOADING -> dialogHelper.showLoadingDialog("Авторизация")
                    NetworkStatus.SUCCESS -> {
                        it.data?.access_token.let { token -> writeToken(prefs, token) }
                        dialogHelper.showSuccessDialog("Уcпешная авторизация")
                        goMainContent()
                    }
                    NetworkStatus.ERROR -> dialogHelper.showErrorDialog("Ошибка")
                }
            }
    }

    private fun goMainContent() {
        val intent = Intent(this, MainContentActivity::class.java)
        dialogHelper.closeActualDialog()
        startActivity(intent)
        finish()
    }

    fun goRegisterActivity(view: View) {
        val intent = Intent(this, RegistrationActivity::class.java)
        val args = Bundle()
        args.putString("email", binding.emailField.text.toString())
        args.putString("pass", binding.passwordField.text.toString())
        intent.putExtras(args)
        startActivity(intent)
    }

    private fun checkUserAuth(prefs: SharedPreferences) {
        when (prefs.getString(TOKEN, null)) {
            null -> {
            }
            else -> {
                goMainContent()
            }
        }
    }

    private fun writeToken(prefs: SharedPreferences, value: String?) {
        with(prefs.edit()) {
            putString(TOKEN, value)
            apply()
        }
    }

}