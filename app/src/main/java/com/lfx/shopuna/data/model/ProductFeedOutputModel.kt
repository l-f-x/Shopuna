package com.lfx.shopuna.data.model

data class ProductFeedOutputModel(
    val has_sale: Boolean,
    val id: Int,
    val is_in_stock: Boolean,
    val price_on_sale: Int,
    val product_description: String,
    val product_name: String,
    val product_price: Int,
    val product_views: Int,
    val product_weight: Int
)