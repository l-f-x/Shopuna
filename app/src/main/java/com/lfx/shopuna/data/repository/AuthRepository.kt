package com.lfx.shopuna.data.repository

import com.lfx.shopuna.data.api.AuthHelper

class AuthRepository(private val helper: AuthHelper) {
    suspend fun login(login: String, password: String) =
        helper.login(login, password)

    suspend fun register(login: String, real_name: String, password: String) =
        helper.register(login, real_name, password)
}