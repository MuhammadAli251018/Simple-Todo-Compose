package com.example.simpletodo.features.utl

/*Todo: Add quick way to handle Result class */
sealed interface Result <T> {
    data class Success<T>(val data: T) : Result<T>
    data class Failure<T>(val err: Throwable) : Result<T>
}