package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.UserTokenInputModel

class UserHelper(private val service: UserService) {
    suspend fun get_self_info(token: String) =
        service.getSelfInfo(UserTokenInputModel(token))
    suspend fun update_realname(token: String, realname: String) =
        service.updateRealName(UserTokenInputModel(token), realname)
}