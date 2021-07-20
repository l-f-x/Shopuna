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
    val token = MutableLiveData<AuthLoginOutputModel>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
       onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun login(login: String, password: String) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.login(login, password)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    token.postValue(response.body())
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
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}