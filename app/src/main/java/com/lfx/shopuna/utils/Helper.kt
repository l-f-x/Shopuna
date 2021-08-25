package com.lfx.shopuna.utils

import com.lfx.shopuna.data.api.RetrofitBuilder

class Helper {
    fun getImageSourceLinkById(id: Int?, token: String?): String{
        return "${RetrofitBuilder.BASE_URL}users/selected_avatar/$id?token=$token"
    }
    fun getImageSourceProductLinkById(product_id: Int?, token: String?): String{
        return "${RetrofitBuilder.BASE_URL}product/photo/$product_id?token=$token"
    }
}