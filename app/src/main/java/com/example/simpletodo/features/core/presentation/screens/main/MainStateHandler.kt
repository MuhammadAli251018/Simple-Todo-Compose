package com.example.simpletodo.features.core.presentation.screens.main

import com.example.simpletodo.features.core.domain.TasksDb.TaskWithId
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.utl.UiState
import com.example.simpletodo.features.utl.UiStateHandler

data class TaskItem(
    val id: Int,
    val title: String,
    val contentHint: String,
    val completed: Boolean,
)

data class MainScreenState(
    val tasks: List<TaskItem>
) : UiState

fun TaskWithId.toTaskItem() = TaskItem(
    id = id.value.toInt(),
    title = task.title,
    contentHint = task.content.getHintFromContent(),
    completed = task.state is Task.TaskState.Done
)

fun List<TaskWithId>.toMainScreenState() = MainScreenState(
    tasks = this.map { it.toTaskItem() }
)

private fun String.getHintFromContent(charLimit: Int = 300): String {
   return if (length/ charLimit.toFloat() > 1.5f) {
        var counter = 0
        val words = split(" ")
       var hint = ""
       for (word in words) {
           if ((counter + word.length) / charLimit <= 1.5) {
               hint + word
               counter ++
           }
       }
       return hint
    }
    else this
}

interface MainStateHandler : UiStateHandler<MainScreenState> {

    fun onTaskChange(id: Int, index: Int, newTask: TaskItem)
}