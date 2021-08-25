package com.lfx.shopuna.data.model

data class ProductAddToCartInputModel(
    val auth: Auth,
    val body: Body
)
data class Body(
    val count: Int,
    val product_id: Int
)
data class Auth(
    val token: String
)