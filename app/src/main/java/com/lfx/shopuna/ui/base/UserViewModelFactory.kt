package com.lfx.shopuna.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.data.repository.UserRepository
import com.lfx.shopuna.ui.viewmodel.UserViewModel

class UserViewModelFactory(private val userHelper: UserHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(UserRepository(userHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}