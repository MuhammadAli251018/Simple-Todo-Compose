package com.example.simpletodo.features.core.domain

import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.utl.Result
import kotlinx.coroutines.flow.Flow


interface TasksDb {
    class TaskId constructor(val id: Long)
    class TaskWithId(val id: TaskId, val task: Task)

    fun insertTask(task: Task): Flow<Result<TaskId>>
    fun updateTask(id: TaskId, newTask: Task): Flow<Result<Unit>>
    fun deleteTask(id: TaskId): Flow<Result<Unit>>
    fun getTask(id: TaskId): Flow<Result<Task>>
    fun getAllTasks(): Flow<Result<List<TaskWithId>>>
}