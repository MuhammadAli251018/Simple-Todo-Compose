package com.example.simpletodo.features.core.presentation.screens.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.domain.entity.Task
import com.example.simpletodo.features.core.domain.entity.Time
import com.example.simpletodo.features.core.presentation.screens.main.MainScreenState
import com.example.simpletodo.features.core.presentation.screens.main.MainStateHandler
import com.example.simpletodo.features.core.presentation.screens.main.TaskItem
import com.example.simpletodo.features.core.presentation.screens.main.toMainScreenState
import com.example.simpletodo.features.utl.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreenVm(
    private val tasksDb: TasksDb
) : ViewModel(), MainStateHandler {
    private var tasks = emptyList<TasksDb.TaskWithId>()

    init {
        loadTasks()
    }

    private val _state = MutableStateFlow(tasks.toMainScreenState())
    override val state: StateFlow<MainScreenState> = _state.asStateFlow()

    override fun onTaskChange(
        id: Int,
        index: Int,
        newTask: TaskItem
    ) {
        tasksDb.updateTask(
            id = TasksDb.TaskId(id.toLong()),
            newTask= tasks[index].task.copy(
                state = if (newTask.completed) Task.TaskState.Done(Time(System.currentTimeMillis())) else Task.TaskState.Undone
            )
        )
    }

    private fun loadTasks() {
       viewModelScope.launch {
            tasksDb.getAllTasks().collectLatest { tasksResult ->
                if (tasksResult is Result.Success) {
                    tasks = tasksResult.data
                }
            }
        }
    }
 }

