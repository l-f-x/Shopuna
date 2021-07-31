package com.lfx.shopuna.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.ui.viewmodel.AuthViewModel

class AuthViewModelFactory(private val authHelper: AuthHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(AuthRepository(authHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}