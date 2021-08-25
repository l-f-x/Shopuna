package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.AuthLoginInputModel
import com.lfx.shopuna.data.model.AuthLoginOutputModel
import com.lfx.shopuna.data.model.AuthRegisterInputModel
import com.lfx.shopuna.data.model.AuthRegisterOutputModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/login")
    suspend fun login(@Body credentials: AuthLoginInputModel): Response<AuthLoginOutputModel>

    @POST("/auth/register")
    suspend fun register(
        @Body data: AuthRegisterInputModel): Response<AuthRegisterOutputModel>
}