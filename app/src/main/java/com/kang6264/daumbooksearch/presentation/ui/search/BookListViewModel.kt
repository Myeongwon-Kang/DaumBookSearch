package com.kang6264.daumbooksearch.presentation.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kang6264.daumbooksearch.presentation.base.BaseViewModel
import com.kang6264.daumbooksearch.data.response.BookDocument
import com.kang6264.daumbooksearch.domain.repository.Repository
import com.kang6264.daumbooksearch.domain.usecase.SearchBookUseCase
import com.kang6264.daumbooksearch.pagingnation.PagingDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@ExperimentalCoroutinesApi
class BookListViewModel @ViewModelInject constructor(
    //private val repository: Repository
    private val searchBookUseCase: SearchBookUseCase
) : BaseViewModel() {
    private val SEARCH_RESULT_LIMIT = 50

    val queryChannel = ConflatedBroadcastChannel<String>()
    val booksLiveData = initSearchListLiveData()
    private var _booksLiveData = MutableLiveData<PagingDataSource>()
    private lateinit var pagingDataSource: PagingDataSource

    init {
        queryChannel.asFlow()
            .onEach { pagingDataSource.invalidate() }
            .launchIn(viewModelScope)
    }

    private fun initSearchListLiveData(): LiveData<PagedList<BookDocument>> {
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(SEARCH_RESULT_LIMIT)
            .setPageSize(SEARCH_RESULT_LIMIT)
            .setEnablePlaceholders(false)
            .build()

        val dataSource = object : DataSource.Factory<Int, BookDocument>() {
            override fun create(): DataSource<Int, BookDocument> {
                return PagingDataSource(
                    queryChannel.valueOrNull ?: "",
                    searchBookUseCase,
                    viewModelScope
                )
                    .also {
                        pagingDataSource = it
                        _booksLiveData.postValue(pagingDataSource)
                    }
            }
        }

        return LivePagedListBuilder(dataSource, config).build()
    }

    fun searchBook(query: String) {
        queryChannel.offer(query)
    }
}