package com.lfx.shopuna.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.lfx.shopuna.R
import com.lfx.shopuna.databinding.ActivityRegistrationBinding

private lateinit var binding: ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        binding.emailField.setText(bundle?.getString("email"))
        binding.passwordField.setText(bundle?.getString("pass"))
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
        val builder = AlertDialog.Builder(this)
        builder.setView(layoutInflater.inflate(R.layout.dialog_success, null))
        builder.create().show()
    }
}