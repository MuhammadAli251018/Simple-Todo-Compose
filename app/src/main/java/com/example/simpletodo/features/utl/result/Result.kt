package com.example.simpletodo.features.utl.result


sealed interface Result <T> {
    data class Success<T>(val data: T) : Result<T>
    //  Todo: Replace Throwable with values of different errors
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

fun <T> Result<T>.getOrNull(): T? = if (this is Result.Success) data else null
fun <T> Result<T>.onSuccess(block: (T) -> Unit) = if (this is Result.Success) block(data) else Unit