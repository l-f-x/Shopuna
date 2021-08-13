package com.lfx.shopuna.ui.base.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lfx.shopuna.data.model.ProductFeedOutputModel
import com.lfx.shopuna.data.repository.ProductRepository
import retrofit2.http.Query

class ProductFeedPageSource(
    private val productRepository: ProductRepository,
    private val query: String
) : PagingSource<Int, ProductFeedOutputModel>()    {


    override fun getRefreshKey(state: PagingState<Int, ProductFeedOutputModel>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductFeedOutputModel> {
        if(query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }
        return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)

    }


}