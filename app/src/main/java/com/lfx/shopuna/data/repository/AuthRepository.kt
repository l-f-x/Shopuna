package com.lfx.shopuna.data.repository

import com.lfx.shopuna.data.api.AuthHelper
import com.lfx.shopuna.data.model.AuthLoginInputModel

class AuthRepository(private val helper: AuthHelper) {
    suspend fun login(login: String, password: String) =
        helper.login(login, password)
}