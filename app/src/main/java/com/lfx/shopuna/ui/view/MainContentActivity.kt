package com.lfx.shopuna.ui.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.ProductHelper
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.ui.base.ProductViewModelFactory
import com.lfx.shopuna.ui.base.UserViewModelFactory
import com.lfx.shopuna.ui.viewmodel.ProductViewModel
import com.lfx.shopuna.ui.viewmodel.UserViewModel
import com.lfx.shopuna.utils.TOKEN
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME

private lateinit var prefs: SharedPreferences

class MainContentActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var productViewModel: ProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(USER_DATA_FILE_NAME, MODE_PRIVATE)
        val userHelper = UserHelper(RetrofitBuilder.userService)
        val productHelper = ProductHelper(RetrofitBuilder.productService)
        userViewModel =
            ViewModelProvider(this, UserViewModelFactory(userHelper)).get(UserViewModel::class.java)
        productViewModel =
            ViewModelProvider(this,ProductViewModelFactory(productHelper)).get(ProductViewModel::class.java)
        val token = prefs.getString(TOKEN, null)
        userViewModel.token.postValue(token)
        productViewModel.token.postValue(token)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
    }
}