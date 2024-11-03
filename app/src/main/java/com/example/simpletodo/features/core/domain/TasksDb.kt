package com.example.simpletodo.features.core.domain

import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.utl.Result


interface TasksDb {
    class TaskId constructor(val id: Long)
    class TaskWithId(val id: TaskId, val task: Task)

    suspend fun insertTask(task: Task): Result<TaskId>
    suspend fun updateTask(id: TaskId, newTask: Task): Result<Unit>
    suspend fun deleteTask(id: TaskId): Result<Unit>
    suspend fun getTask(id: TaskId): Result<Task>
    suspend fun getAllTasks(): Result<List<TaskWithId>>
}