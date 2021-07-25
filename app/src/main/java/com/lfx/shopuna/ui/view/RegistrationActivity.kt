package com.lfx.shopuna.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.databinding.ActivityRegistrationBinding
import com.lfx.shopuna.ui.base.ViewModelFactory
import com.lfx.shopuna.ui.viewmodel.AuthViewModel
import com.lfx.shopuna.utils.NetworkDialogUtils

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityRegistrationBinding

@SuppressLint("StaticFieldLeak")
private lateinit var dialogHelper: NetworkDialogUtils
class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.emailField.setText(bundle!!.getString("email", ""))
        binding.passwordField.setText(bundle.getString("pass", ""))
        dialogHelper = NetworkDialogUtils(this, layoutInflater)
    }

    fun goAuthActivity(view: View) {
        val intent = Intent(this,LoginActivity::class.java)
        val args = Bundle()
        args.putString("email", binding.emailField.text.toString())
        args.putString("pass", binding.passwordField.text.toString())
        intent.putExtras(args)
        startActivity(intent)
    }
    fun register(view: View) {
        val authHelper = AuthHelper(RetrofitBuilder.authService)
        val viewModel =
            ViewModelProvider(this, ViewModelFactory(authHelper)).get(AuthViewModel::class.java)

        viewModel.errorMessage.observe(this) {
            dialogHelper.showErrorDialog(it)
        }
        viewModel.success.observe(this, {
            if (it) {
                dialogHelper.showSuccessDialog("Успешная регистрация!")
            }
        })

        dialogHelper.showLoadingDialog("Регистрация")
        viewModel.register(
            binding.emailField.text.toString(),
            binding.fullNameField.text.toString(),
            binding.passwordField.text.toString()
        )

    }
}