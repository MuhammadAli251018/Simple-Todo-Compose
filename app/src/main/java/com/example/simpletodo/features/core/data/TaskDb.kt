package com.example.simpletodo.features.core.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.core.domain.entity.Time

@Entity(tableName = "tasks")
internal data class TaskDb(
    @PrimaryKey
    val id: Int,
    val title: String,
    val content: String,
    val issueTime: Long,
    val completeTime: Long
)
internal const val NOT_COMPLETED = -5L


internal fun Task.toTaskDb(id: Int = 0): TaskDb = TaskDb(
    id = id,
    title = title,
    content = content,
    issueTime = issueTime.value,
    completeTime = if (state is Task.TaskState.Done) state.finishTime.value else NOT_COMPLETED
)
internal fun TaskDb.toTask(): Task = Task(
    title = title,
    content = content,
    issueTime = Time(issueTime),
    state = if (completeTime != NOT_COMPLETED) Task.TaskState.Done(Time(completeTime)) else Task.TaskState.Undone
)