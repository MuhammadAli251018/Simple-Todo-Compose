package com.example.simpletodo.features.core.presentation.screens.task

import com.example.simpletodo.features.utl.UiState
import com.example.simpletodo.features.utl.UiStateHandler

data class TaskState(
    val title: String,
    val content: String,
    val state: String,
    val issueTime: String
) : UiState

interface TaskStateHandler : UiStateHandler<TaskState> {
    fun onTitleChange(newTitle: String)
    fun onContentChange(newContent: String)
    fun onStateChange(newState: String)
    fun onSaveButton()
}