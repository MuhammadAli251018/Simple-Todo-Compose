package com.example.simpletodo.features.core.presentation.screens.task.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.core.domain.entity.Time
import com.example.simpletodo.features.core.presentation.screens.task.TaskState
import com.example.simpletodo.features.core.presentation.screens.task.TaskStateHandler
import com.example.simpletodo.features.core.presentation.screens.task.toTaskState
import com.example.simpletodo.features.utl.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
sealed class StartMode {
    data object NewTask : StartMode()
    data class ViewTask(val taskId: Long) : StartMode()
}

class TaskScreenVm(
   private val startMode:  StartMode,
    private val tasksDb: TasksDb
) : ViewModel(), TaskStateHandler {

    private var initialTask: Task = Task.getEmptyTask()

    init {
        if (startMode is StartMode.ViewTask)
            viewModelScope.launch {
                tasksDb.getTask(TasksDb.TaskId(startMode.taskId)).collectLatest { result ->
                    if (result is Result.Success)
                        initialTask = result.data
                }
            }
    }
    private val _state = MutableStateFlow(initialTask.toTaskState())
    override val state: StateFlow<TaskState> = _state.asStateFlow()

    override fun onTitleChange(newTitle: String) {
        _state.apply {
            this.taskState = this.taskState.copy(title = newTitle)
        }
    }

    override fun onContentChange(newContent: String) {
        _state.apply {
            this.taskState = this.taskState.copy(content = newContent)
        }
    }

    override fun onStateChange(newState: Boolean) {
        _state.apply {
            this.taskState = this.taskState.copy(taskState = newState)
        }
    }

    override fun onSaveButton() {
        // Todo create a waiting state. And handle errors
        if (startMode is StartMode.NewTask) {
            tasksDb.insertTask(
                task = initialTask.copy(
                    title = _state.value.title,
                    content = _state.value.content,
                    // Todo: Make a mapper from state
                    state = if (_state.value.taskState) Task.TaskState.Done(Time(value = (initialTask.state as Task.TaskState.Done).finishTime.value)) else Task.TaskState.Undone
                )
            )
        }
        else {
            tasksDb.updateTask(
                id = TasksDb.TaskId((startMode as StartMode.ViewTask).taskId),
                newTask = initialTask.copy(
                    title = _state.value.title,
                    content = _state.value.content,
                    // Todo: Make a mapper from Task domain to TaskState
                    state = if (_state.value.taskState)
                        Task.TaskState.Done(Time(value = (initialTask.state as Task.TaskState.Done).finishTime.value))
                    else Task.TaskState.Undone
                )
            )
        }
    }

    // Todo disable the delete button if mode is new Task
    override fun onDeleteButtonClick() {
        if (startMode is StartMode.ViewTask)
            tasksDb.deleteTask(TasksDb.TaskId(startMode.taskId))
    }
}