package com.lfx.shopuna.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.utils.Resource
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    fun login(login: String, password: String) = liveData(Dispatchers.IO){
        emit(Resource.loading(data = null, msg = "Авторизация"))
        try {
            emit(Resource.success(data = repository.login(login, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }
}