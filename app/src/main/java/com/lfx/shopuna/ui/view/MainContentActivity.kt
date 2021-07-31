package com.lfx.shopuna.ui.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lfx.shopuna.R
import com.lfx.shopuna.utils.USER_DATA_FILE_NAME

private lateinit var prefs: SharedPreferences

class MainContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        prefs = getSharedPreferences(USER_DATA_FILE_NAME, MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
    }
}