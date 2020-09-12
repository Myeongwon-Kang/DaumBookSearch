package com.kang6264.daumbooksearch.domain.usecase

import com.kang6264.daumbooksearch.data.response.BookResponse
import com.kang6264.daumbooksearch.presentation.di.IoDispatcher
import com.kang6264.daumbooksearch.domain.data.RequestParams
import com.kang6264.daumbooksearch.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val repository: Repository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : UseCase<RequestParams, BookResponse>(dispatcher) {
    override suspend fun execute(parameters: RequestParams): BookResponse {
        return repository.searchBookList(
            parameters.query, parameters.page, parameters.size, parameters.sort, parameters.target
        )
    }
}
