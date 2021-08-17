package com.lfx.shopuna.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lfx.shopuna.data.api.ProductHelper
import com.lfx.shopuna.data.api.UserHelper
import com.lfx.shopuna.data.repository.ProductRepository
import com.lfx.shopuna.data.repository.UserRepository
import com.lfx.shopuna.ui.viewmodel.ProductViewModel
import com.lfx.shopuna.ui.viewmodel.UserViewModel

class ProductViewModelFactory(private val productHelper: ProductHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            return ProductViewModel(ProductRepository(productHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}