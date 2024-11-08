package com.example.simpletodo.features.utl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow

/*Todo: Add quick way to handle Result class */
sealed interface Result <T> {
    data class Success<T>(val data: T) : Result<T>
    data class Failure<T>(val err: Throwable) : Result<T>
}

inline fun <T> getResult(operation: () -> T): Result<T>  {
    return try {
        Result.Success(operation())
    }
    catch (e: Exception) {
        Result.Failure(e)
    }
}