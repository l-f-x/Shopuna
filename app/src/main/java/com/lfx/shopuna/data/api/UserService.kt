package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.UserSelfInfoOutputModel
import com.lfx.shopuna.data.model.UserTokenInputModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface UserService {
    @POST("/users/get_self_info")
    suspend fun getSelfInfo(@Body auth: UserTokenInputModel): Response<UserSelfInfoOutputModel>
    @PUT("/user/update_realname")
    suspend fun  updateRealName(@Body token: UserTokenInputModel, @Query("realname") realname: String): Response<String>
}