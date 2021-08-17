package com.lfx.shopuna.data.api

class ProductHelper(val service: ProductService) {
    suspend fun feed(token : String, count : Int, offset : Int) =
        service.getFeed(token, count, offset)

    suspend fun get_cart(token: String) =
        service.getCart(token)
}