package com.lfx.shopuna.ui.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.ui.base.UserViewModelFactory
import com.lfx.shopuna.ui.viewmodel.UserViewModel
import com.lfx.shopuna.utils.TOKEN
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME

private lateinit var prefs: SharedPreferences

class MainContentActivity : AppCompatActivity() {
    lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(USER_DATA_FILE_NAME, MODE_PRIVATE)
        val userHelper = UserHelper(RetrofitBuilder.userService)
        val viewModel =
            ViewModelProvider(this, UserViewModelFactory(userHelper)).get(UserViewModel::class.java)
        val token = prefs.getString(TOKEN, null)
        viewModel.token.postValue(token)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
    }
}