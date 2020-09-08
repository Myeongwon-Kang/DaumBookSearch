package com.kang6264.daumbooksearch.data.datasource

import com.kang6264.daumbooksearch.data.response.BookResponse

interface RemoteDataSource {
    suspend fun searchBookList(query: String, page: Int, size: Int) : BookResponse
}