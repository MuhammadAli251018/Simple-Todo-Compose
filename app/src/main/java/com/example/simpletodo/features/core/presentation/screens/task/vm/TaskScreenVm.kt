package com.example.simpletodo.features.core.presentation.screens.task.vm

import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.simpletodo.features.core.di.tasksDbModule
import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.core.presentation.screens.task.TaskState
import com.example.simpletodo.features.core.presentation.screens.task.TaskStateHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class StartMode {
    data object NewTask : StartMode()
    data class ViewTask(val taskId: Long) : StartMode()
}

class TaskScreenVm(
   private val startMode:  StartMode,
    private val tasksDb: TasksDb
) : ViewModel(), TaskStateHandler {

    private val _state = MutableStateFlow(TaskState( // Todo
        title = "",
        content = "",
        state = false,
        issueTime = System.currentTimeMillis().toString()
    ))
    override val state: StateFlow<TaskState> = _state.asStateFlow()

    override fun onTitleChange(newTitle: String) {
        _state.apply {
            value = value.copy(title = newTitle)
        }
    }

    override fun onContentChange(newContent: String) {
        _state.apply {
            value = value.copy(content = newContent)
        }
    }

    override fun onStateChange(newState: Boolean) {
        _state.apply {
            value = value.copy(state = newState)
        }
    }

    override fun onSaveButton() {
        if (startMode is StartMode.NewTask)
           TODO() //  Todo: Add the new task
        else
           TODO() // Todo: Update
    }

    override fun onDeleteButtonClick() {
    }
}