package com.example.simpletodo.features.core.di

import androidx.room.Room
import com.example.simpletodo.features.core.data.RoomTasksDbImp
import com.example.simpletodo.features.core.data.TasksDatabase
import com.example.simpletodo.features.core.domain.TasksDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val tasksDbModule = module {
    single{
        Room.databaseBuilder(
            androidContext(),
            TasksDatabase::class.java,
            "tasks-db"
        ).build().tasksDao()
    }
    single<TasksDb> { RoomTasksDbImp(get()) }
}