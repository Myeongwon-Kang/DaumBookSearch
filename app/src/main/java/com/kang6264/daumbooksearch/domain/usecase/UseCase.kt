package com.kang6264.daumbooksearch.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import com.kang6264.daumbooksearch.presentation.util.Result
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.lang.Exception

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P) : Result<R>{
        return try{
            withContext(coroutineDispatcher){
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        }catch (e: Exception){
            Timber.d(e)
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}

