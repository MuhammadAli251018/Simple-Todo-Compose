package com.example.simpletodo.features.core.presentation.screens.main

import com.example.simpletodo.features.utl.UiState
import com.example.simpletodo.features.utl.UiStateHandler

data class TaskItem(
    val title: String,
    val contentHint: String,
    val completed: Boolean,
)

data class MainScreenState(
    val tasks: List<TaskItem>
) : UiState

interface MainStateHandler : UiStateHandler<MainScreenState> {

    fun onTaskChange(index: Int, newTask: TaskItem)
}