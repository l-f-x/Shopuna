package com.lfx.shopuna.data.repository

import com.lfx.shopuna.data.api.ProductHelper

class ProductRepository(val helper: ProductHelper) {
    suspend fun feed(token : String, count : Int, offset : Int) =
        helper.feed(token, count, offset)
}