package com.example.simpletodo.features.core.presentation.screens.main.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodo.features.core.domain.TasksDb
import com.example.simpletodo.features.core.presentation.screens.main.MainScreenState
import com.example.simpletodo.features.core.presentation.screens.main.MainStateHandler
import com.example.simpletodo.features.core.presentation.screens.main.TaskItem
import com.example.simpletodo.features.utl.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainScreenVm(
    private val tasksDb: TasksDb
) : ViewModel(), MainStateHandler {
    private var tasks = emptyList<TasksDb.TaskWithId>()
        set(value) {
            field = value
            _state.value = TODO()
        }
    private val _state = MutableStateFlow(tasks)

    init {
        viewModelScope.launch {
            tasksDb.getAllTasks().collectLatest { tasksResult ->
                if(tasksResult is Result.Success)
                    tasks = tasksResult.data
            }
        }
    }
    override val state: StateFlow<MainScreenState>
        get() = TODO("Not yet implemented")

    override fun onTaskChange(
        index: Int,
        newTask: TaskItem
    ) {
        TODO("Not yet implemented")
    }
}

