package com.example.simpletodo.features.core.data

import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.utl.getResult

internal class RoomTasksDbImp(
    private val tasksDao: TasksDao
    
) : TasksDb {
    override suspend fun insertTask(task: Task) = getResult {
            val id = tasksDao.insertTask(task.toTaskDb())
            TasksDb.TaskId(id)
    }


    override suspend fun updateTask(id: TasksDb.TaskId, newTask: Task) = getResult {
        tasksDao.updateTask(newTask.toTaskDb(id = id.id.toInt()))
    }

    override suspend fun deleteTask(id: TasksDb.TaskId) = getResult {
        tasksDao.deleteTask(id.id.toInt())
    }

    override suspend fun getTask(id: TasksDb.TaskId) = getResult {
        tasksDao.getTask(id = id.id.toInt()).toTask()
    }

    override suspend fun getAllTasks() = getResult {
        tasksDao.getAllTasks().map { TasksDb.TaskWithId(id = (TasksDb.TaskId(it.id.toLong())), it.toTask()) }
    }
}