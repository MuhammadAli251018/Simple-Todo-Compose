package com.example.simpletodo.features.utl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

//  Todo: Unit Test
inline fun <T, R> StateFlow<T>.map(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
    crossinline transform: T.() -> R
): StateFlow<R> {
    val transFlow = MutableStateFlow<R>(transform(value))

    CoroutineScope(coroutineContext).launch {
        collectLatest { newValue ->
            //  Only emits new Changes
            val hasChanged = transFlow.value != transform(newValue)
            if (hasChanged)
                transFlow.emit(transform(newValue))
        }
    }
    return transFlow
}

@Composable
inline fun <S, P> StateFlow<S>
        .collectPropertyAsState(crossinline transform: S.() -> P): State<P>
            = map(rememberCoroutineScope().coroutineContext, transform).collectAsState()
