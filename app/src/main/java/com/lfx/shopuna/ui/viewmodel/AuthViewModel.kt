package com.lfx.shopuna.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.utils.Resource
import kotlinx.coroutines.Dispatchers

class AuthViewModel(private val repository: AuthRepository): ViewModel() {



    fun login(login: String, password: String) = liveData<Resource<Any>>(Dispatchers.IO) {
        emit(Resource.loading(data = null, msg = null))
        try {
            val response = repository.login(login, password)
            if (response.code() == 200) {
                emit(Resource.success(data = response))
            } else {
                emit(Resource.error(response.body()?.detail, null))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.toString(), null))
        }
    }

    fun register(login: String, real_name: String, password: String) =
        liveData<Resource<Any>>(Dispatchers.IO) {
            emit(Resource.loading(data = null, msg = null))
            try {
                val response = repository.register(login, real_name, password)
                if (response.code() == 200) {
                    emit(Resource.success(data = response))
                } else {
                    emit(Resource.error(response.body()?.detail, null))
                }
            } catch (exception: Exception) {
                emit(Resource.error(exception.toString(), null))
            }

        }

}