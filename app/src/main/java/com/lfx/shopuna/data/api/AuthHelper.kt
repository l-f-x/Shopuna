package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.AuthLoginInputModel
import com.lfx.shopuna.data.model.AuthRegisterInputModel

class AuthHelper(private val service:AuthService) {
    suspend fun login(login: String, password: String) =
        service.login(AuthLoginInputModel(login, password))

    suspend fun register(login: String, real_name: String, password: String) =
        service.register(AuthRegisterInputModel(login, real_name, password))
}