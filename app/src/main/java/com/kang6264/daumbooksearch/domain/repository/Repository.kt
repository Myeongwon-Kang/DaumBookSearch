package com.kang6264.daumbooksearch.domain.repository

import com.kang6264.daumbooksearch.data.response.BookResponse

interface Repository {
    suspend fun searchBookList(query: String, page: Int, size: Int): BookResponse
}