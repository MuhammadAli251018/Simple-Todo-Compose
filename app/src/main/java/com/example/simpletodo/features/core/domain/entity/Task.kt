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

    companion object {
        fun getEmptyTask(title: String = "",
                         content: String = "",
                         issueTime: Time = Time(System.currentTimeMillis()),
                         state: TaskState = TaskState.Undone
        ) = Task(title = title, content = content, issueTime = issueTime, state = state)
    }
}


