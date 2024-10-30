package com.example.simpletodo.features.core.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskDb::class], version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}