package com.kang6264.daumbooksearch.pagingnation

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.domain.data.Params
import com.kang6264.daumbooksearch.domain.repository.Repository
import com.kang6264.daumbooksearch.domain.usecase.SearchBookUseCase
import com.kang6264.daumbooksearch.presentation.util.data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagingDataSource(
    private val query: String,
    //private val repository: Repository,
    private val searchBookUseCase: SearchBookUseCase,
    private val viewModelScope: CoroutineScope
) : PageKeyedDataSource<Int, BookDocument>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookDocument>
    ) {
        viewModelScope.launch {
            try {
                //repository.searchBookList(query, 1, params.requestedLoadSize).let {
                searchBookUseCase(Params(query, 1, params.requestedLoadSize)).let {
                    callback.onResult(it.data.documents, null, 2)
                }
            } catch (exception: Exception) {
                callback.onResult(emptyList(), null, null)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, BookDocument>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, BookDocument>) {
        viewModelScope.launch {
            try {
                //repository.searchBookList(query, params.key, params.requestedLoadSize).let {
                searchBookUseCase(Params(query, params.key, params.requestedLoadSize)).let {
                    callback.onResult(it.data.documents, params.key.plus(1))
                }
            } catch (exception: Exception) {
                callback.onResult(emptyList(), null)
            }
        }
    }
}