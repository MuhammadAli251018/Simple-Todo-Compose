package com.example.simpletodo.features.core.presentation.screens.core

import com.example.simpletodo.features.utl.UiState
import com.example.simpletodo.features.utl.UiStateHandler

data class Task(
    val title: String,
    val contentHint: String,
    val completed: Boolean,
)

data class CoreScreenState(
    val tasks: List<Task>
) : UiState

interface CoreStateHandler : UiStateHandler<CoreScreenState> {
    
}