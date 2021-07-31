package com.lfx.shopuna.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lfx.shopuna.data.repository.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel()