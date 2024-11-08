package com.example.simpletodo.features.core.data

import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.utl.result.getResult
import kotlinx.coroutines.flow.flow

class RoomTasksDbImp(private val tasksDao: TasksDao) : TasksDb {
    override fun insertTask(task: Task) = flow {
        emit(getResult {
            val id = tasksDao.insertTask(task.toTaskDb())
            TasksDb.TaskId(id)
        })
    }


    override fun updateTask(id: TasksDb.TaskId, newTask: Task) = flow{
        emit(getResult {
            tasksDao.updateTask(newTask.toTaskDb(id = id.id.toInt()))
        })
    }

    override fun deleteTask(id: TasksDb.TaskId) = flow{
        emit(getResult {
            tasksDao.deleteTask(id.id.toInt())
        })
    }

    override fun getTask(id: TasksDb.TaskId) = flow{
       emit(getResult {
            tasksDao.getTask(id = id.id.toInt()).toTask()
        })
    }

    override fun getAllTasks() = flow{
        emit(getResult {
            tasksDao.getAllTasks()
                .map { TasksDb.TaskWithId(id = (TasksDb.TaskId(it.id.toLong())), it.toTask()) }
        })
    }
}