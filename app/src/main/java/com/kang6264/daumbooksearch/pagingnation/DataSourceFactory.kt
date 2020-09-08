package com.kang6264.daumbooksearch.pagingnation

import android.util.Log
import androidx.paging.DataSource
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.domain.repository.Repository
import kotlinx.coroutines.CoroutineScope

class DataSourceFactory(
    private val query: String,
    private val repository: Repository,
    private val viewModelScope: CoroutineScope
) : DataSource.Factory<Int, BookDocument>() {
    override fun create(): DataSource<Int, BookDocument> {
        Log.d("순서 =", "DataSourceFactory()")
        return PagingDataSource(query, repository, viewModelScope)
    }
}