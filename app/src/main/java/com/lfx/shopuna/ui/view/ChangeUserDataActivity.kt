package com.lfx.shopuna.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.R
import com.lfx.shopuna.data.api.RetrofitBuilder
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.ui.base.UserViewModelFactory
import com.lfx.shopuna.ui.viewmodel.UserViewModel
import com.lfx.shopuna.utils.NetworkStatus

class ChangeUserDataActivity : AppCompatActivity() {
    lateinit var viewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_user_data)
        val bundle = intent.extras
        val userHelper = UserHelper(RetrofitBuilder.userService)
        viewModel =
            ViewModelProvider(this, UserViewModelFactory(userHelper)).get(UserViewModel::class.java)
        viewModel.token.postValue(bundle?.getString("token"))


    }

    fun apply(view: View) {
        viewModel.updateRealName("test t").observe(this){
            when(it.status){
                NetworkStatus.SUCCESS->{
                    val intent = Intent(this,MainContentActivity::class.java)
                    startActivity(intent)
                }
                NetworkStatus.ERROR -> Toast.makeText(this,"dwq",Toast.LENGTH_SHORT).show()
                NetworkStatus.LOADING -> {}
            }
        }

    }
}