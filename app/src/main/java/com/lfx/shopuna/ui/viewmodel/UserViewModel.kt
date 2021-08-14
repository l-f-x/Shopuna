package com.lfx.shopuna.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lfx.shopuna.data.repository.UserRepository
import com.lfx.shopuna.utils.Resource
import kotlinx.coroutines.Dispatchers

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val token = MutableLiveData<String?>()

    fun getSelfInfo() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null, msg = null))
        try {
            val response = repository.get_self_info(token.value!!)
            if (response.code() == 200) {
                emit(Resource.success(data = response.body()))
            } else {
                emit(Resource.error(response.body()?.detail, null))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.toString(), null))
        }
    }

    fun updateRealName(realname: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null, msg = null))
        try {
            val response = repository.update_realname(token.value!!, realname)
            if (response.code() == 200) {
                emit(Resource.success(data = response.body()))
            } else {
                emit(Resource.error(response.errorBody().toString(), null))
            }
        } catch (exception: Exception) {
            emit(Resource.error(exception.toString(), null))
        }
    }
}