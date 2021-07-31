package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.UserSelfInfoOutputModel
import com.lfx.shopuna.data.model.UserTokenInputModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user/get_self_info")
    suspend fun getSelfInfo(@Body auth: UserTokenInputModel): Response<UserSelfInfoOutputModel>
}