package com.lfx.shopuna.data.api

import com.lfx.shopuna.data.model.ProductFeedOutputModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {
    @GET("/product/feed")
    suspend fun getFeed(
        @Query("token") token:String?,
        @Query("count") count:Int,
        @Query("offset")  offset:Int
    ) : Response<List<ProductFeedOutputModel>>
}