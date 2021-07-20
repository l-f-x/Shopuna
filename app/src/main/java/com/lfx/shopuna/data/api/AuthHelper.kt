package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.AuthLoginInputModel

class AuthHelper(private val service:AuthService) {
    suspend fun login(login: String, password: String) =
        service.login(AuthLoginInputModel(login, password))
}