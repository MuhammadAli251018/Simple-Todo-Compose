package com.example.simpletodo.features.core.presentation.screens.task.vm

import androidx.lifecycle.ViewModel
import com.example.simpletodo.features.core.presentation.screens.task.TaskState
import com.example.simpletodo.features.core.presentation.screens.task.TaskStateHandler
import kotlinx.coroutines.flow.StateFlow

class TaskScreenVm(taskId: Long) : ViewModel(), TaskStateHandler {

    override fun onTitleChange(newTitle: String) {

    }

    override fun onContentChange(newContent: String) {

    }

    override fun onStateChange(newState: Boolean) {

    }

    override fun onSaveButton() {

    }

    override fun onDeleteButtonClick() {

    }

    override val state: StateFlow<TaskState>
        get() = TODO("Nt yet implemented")
}