package com.kang6264.daumbooksearch.presentation.pagingnation

import androidx.paging.PageKeyedDataSource
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.domain.data.RequestParams
import com.kang6264.daumbooksearch.domain.usecase.SearchBookUseCase
import com.kang6264.daumbooksearch.presentation.util.data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PagingDataSource(
    private val requestRequestParams: RequestParams,
    private val searchBookUseCase: SearchBookUseCase,
    private val viewModelScope: CoroutineScope
) : PageKeyedDataSource<Int, BookDocument>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, BookDocument>
    ) {
        viewModelScope.launch {
            try {
                val requestParams = requestRequestParams.apply {
                    page = 1
                    size = params.requestedLoadSize
                }
                searchBookUseCase(requestParams).let {
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
                val requestParams = requestRequestParams.apply {
                    page = params.key
                    size = params.requestedLoadSize
                }
                searchBookUseCase(requestParams).let {
                    callback.onResult(it.data.documents, params.key.plus(1))
                }
            } catch (exception: Exception) {
                callback.onResult(emptyList(), null)
            }
        }
    }
}