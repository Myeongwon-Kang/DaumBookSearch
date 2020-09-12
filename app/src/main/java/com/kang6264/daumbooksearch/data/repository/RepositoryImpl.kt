package com.kang6264.daumbooksearch.data.repository

import com.kang6264.daumbooksearch.data.datasource.RemoteDataSource
import com.kang6264.daumbooksearch.data.response.BookResponse
import com.kang6264.daumbooksearch.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun searchBookList(
        query: String,
        page: Int,
        size: Int,
        sort: String,
        target: String
    ): BookResponse {
        return remoteDataSource.searchBookList(query, page, size, sort, target)
    }
}