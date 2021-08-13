package com.lfx.shopuna.utils

import com.lfx.shopuna.data.api.RetrofitBuilder

class Helper {
    fun getImageSourceLinkById(product_id: Int?, token: String?): String{
        return "${RetrofitBuilder.BASE_URL}users/selected_avatar/$product_id?token=$token"
    }
}