package com.example.simpletodo.features.core.presentation.screens.task.vm

import androidx.lifecycle.ViewModel
import com.example.simpletodo.features.core.presentation.screens.task.TaskState
import com.example.simpletodo.features.core.presentation.screens.task.TaskStateHandler
import kotlinx.coroutines.flow.StateFlow

class TaskScreenVm(taskId: Long) : ViewModel(), TaskStateHandler {

    override fun onTitleChange(newTitle: String) {
        TODO("Not yet implemented")
    }

    override fun onContentChange(newContent: String) {
        TODO("Not yet implemented")
    }

    override fun onStateChange(newState: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSaveButton() {
        TODO("Not yet implemented")
    }

    override val state: StateFlow<TaskState>
        get() = TODO("Not yet implemented")
}