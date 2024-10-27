package com.example.simpletodo.features.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpletodo.features.core.domain.TasksDb

@Database(entities = [TasksDb::class], version = 1)
internal abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}