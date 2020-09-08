package com.kang6264.daumbooksearch.data.api

import com.kang6264.daumbooksearch.data.response.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DaumApi {
    @GET("v3/search/book")
    suspend fun searchBookList(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("sort") sort: String?,
        @Query("target") target: String?
    ) : BookResponse
}