package com.example.simpletodo.features.core.domain.entity


data class Task(
    val title: String,
    val content: String,
    val issueTime: Time,
    val state: TaskState
) {
    sealed interface TaskState {
        data class Done(val finishTime: Time) : TaskState
        data object Undone : TaskState
    }
}
