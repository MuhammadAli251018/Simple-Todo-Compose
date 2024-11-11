package com.example.simpletodo.features.core.di

import androidx.room.Room
import com.example.simpletodo.features.core.data.RoomTasksDbImp
import com.example.simpletodo.features.core.data.TasksDatabase
import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.presentation.screens.main.vm.MainScreenVm
import com.example.simpletodo.features.core.presentation.screens.task.vm.TaskScreenVm
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val coreFeatModule = module {
    single{
        Room.databaseBuilder(
            androidContext(),
            TasksDatabase::class.java,
            "tasks-db"
        ).build().tasksDao()
    }
    single<TasksDb> { RoomTasksDbImp(get()) }

    viewModel{MainScreenVm(get())}

    viewModel{ TaskScreenVm(get(), get())}
}