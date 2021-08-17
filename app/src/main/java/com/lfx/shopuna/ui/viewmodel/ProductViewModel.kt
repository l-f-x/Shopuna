package com.lfx.shopuna.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lfx.shopuna.data.model.ProductGetCartOutputModel
import com.lfx.shopuna.data.repository.ProductRepository
import com.lfx.shopuna.data.repository.UserRepository
import com.lfx.shopuna.utils.Resource
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val token = MutableLiveData<String?>()

    fun cart() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null, msg = null))
        try {
            val response = repository.get_cart(token.value!!)
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