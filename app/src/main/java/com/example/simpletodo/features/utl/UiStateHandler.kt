package com.example.simpletodo.features.utl

import kotlinx.coroutines.flow.StateFlow

interface UiState

interface UiStateHandler<State: UiState> {
    val state: StateFlow<State>
    fun updateState(block: State.() -> State)
}