package com.lfx.shopuna.data.repository

import com.lfx.shopuna.data.api.UserHelper


class UserRepository(private val helper: UserHelper) {
    suspend fun get_self_info(token: String) =
        helper.get_self_info(token)
    suspend fun  update_realname(token: String, realname: String) =
        helper.update_realname(token, realname)
}