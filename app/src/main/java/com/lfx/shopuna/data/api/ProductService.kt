package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.ProductAddToCartInputModel
import com.lfx.shopuna.data.model.ProductFeedOutputModel
import com.lfx.shopuna.data.model.ProductGetCartOutputModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductService {
    @GET("/product/feed")
    suspend fun getFeed(
        @Query("token") token:String?,
        @Query("count") count:Int,
        @Query("offset")  offset:Int
    ) : Response<List<ProductFeedOutputModel>>

    @GET("/product/cart")
    suspend fun getCart(
        @Query("token") token:String?
    ) : Response<List<ProductGetCartOutputModel>>

    @POST("/product/add_to_cart")
    suspend fun addToCart(
        @Body data: ProductAddToCartInputModel) :Response<String>
}