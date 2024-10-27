package com.example.simpletodo.features.core.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
internal interface TasksDao {

    @Insert
    suspend fun insertTask(task: TaskDb): Long

    @Update
    suspend fun updateTask(task: TaskDb)

    @Delete
    suspend fun deleteTask(id: Int)

    @Query("DELETE FROM tasks WHERE id = :id")
    suspend fun getTask(id: Int): TaskDb

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskDb>

}