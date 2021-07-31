package com.lfx.shopuna.data.model

import java.util.*

data class UserSelfInfoOutputModel(
    val id: Int,
    val real_name: String,
    val login: String,
    val role: String,
    val balance: Int,
    val register_date: Date
)
