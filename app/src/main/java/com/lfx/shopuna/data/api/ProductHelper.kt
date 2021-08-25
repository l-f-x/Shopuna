package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.Auth
import com.lfx.shopuna.data.model.Body
import com.lfx.shopuna.data.model.ProductAddToCartInputModel

class ProductHelper(val service: ProductService) {
    suspend fun feed(token : String, count : Int, offset : Int) =
        service.getFeed(token, count, offset)

    suspend fun get_cart(token: String) =
        service.getCart(token)

    suspend fun add_to_cart(token: String, id: Int, count: Int) =
        service.addToCart(ProductAddToCartInputModel(Auth(token), Body(count,id)))

}