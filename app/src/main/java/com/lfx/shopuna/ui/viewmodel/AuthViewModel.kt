package com.lfx.shopuna.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lfx.shopuna.data.model.AuthLoginOutputModel
import com.lfx.shopuna.data.repository.AuthRepository
import com.lfx.shopuna.utils.Resource
import kotlinx.coroutines.*

class AuthViewModel(private val repository: AuthRepository): ViewModel() {
    private val TAG = "AuthViewModel"
    val errorMessage = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()
    val token = MutableLiveData<AuthLoginOutputModel>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun login(login: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.login(login, password)
            withContext(Dispatchers.Main) {
                loading.value = true
                if (response.isSuccessful && response.code() == 200) {
                    success.value = true
                    loading.value = false
                    token.postValue(response.body())
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    fun test(login: String, password: String) = liveData<Resource<Any>>(Dispatchers.IO) {
        emit(Resource.loading(data = null, msg = null))
        try {
            emit(Resource.success(data = repository.login(login, password)))
        } catch (exception: Exception) {
            emit(Resource.error(exception.toString(), null))
        }
    }

    fun register(login: String, real_name: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.register(login, real_name, password)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && response.code() == 200) {
                    success.value = true
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        Log.d(TAG, "Exception handled: $message}")
        errorMessage.value = message
        loading.value = false
        success.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}