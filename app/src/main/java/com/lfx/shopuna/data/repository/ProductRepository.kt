package com.lfx.shopuna.data.repository

import com.lfx.shopuna.data.api.ProductHelper

class ProductRepository(val helper: ProductHelper) {
    suspend fun feed(token : String, count : Int, offset : Int) =
        helper.feed(token, count, offset)

    suspend fun get_cart(token: String) =
        helper.get_cart(token)
    suspend fun add_to_cart(token: String, id: Int, count: Int) =
        helper.add_to_cart(token,id,count)
}