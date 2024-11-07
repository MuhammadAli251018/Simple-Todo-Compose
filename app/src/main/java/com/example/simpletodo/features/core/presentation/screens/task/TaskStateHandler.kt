package com.example.simpletodo.features.core.presentation.screens.task

import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.core.domain.entity.Time
import com.example.simpletodo.features.utl.UiState
import com.example.simpletodo.features.utl.UiStateHandler

data class TaskState(
    val title: String,
    val content: String,
    val state: Boolean,
    val issueTime: String
) : UiState

fun Task.toTaskState() = TaskState(
    title = title,
    content = content,
    issueTime = issueTime.value.toString(),
    state = if (state is Task.TaskState.Undone) false else true
)

interface TaskStateHandler : UiStateHandler<TaskState> {
    fun onTitleChange(newTitle: String)
    fun onContentChange(newContent: String)
    fun onStateChange(newState: Boolean)
    fun onSaveButton()
    fun onDeleteButtonClick()
}