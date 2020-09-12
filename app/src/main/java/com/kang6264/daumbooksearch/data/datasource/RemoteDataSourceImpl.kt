package com.kang6264.daumbooksearch.data.datasource

import com.kang6264.daumbooksearch.data.api.DaumApi
import com.kang6264.daumbooksearch.data.datasource.RemoteDataSource
import com.kang6264.daumbooksearch.data.response.BookResponse
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val daumApi: DaumApi
) : RemoteDataSource {
    override suspend fun searchBookList(
        query: String,
        page: Int,
        size: Int,
        sort: String,
        target: String
    ): BookResponse {
        return daumApi.searchBookList(query = query, page = page, size = size, sort, target)
    }
}