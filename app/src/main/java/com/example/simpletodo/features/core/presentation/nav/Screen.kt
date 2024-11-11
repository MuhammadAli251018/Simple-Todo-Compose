package com.example.simpletodo.features.core.presentation.nav

import com.example.simpletodo.features.core.presentation.screens.task.vm.StartMode
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object MainScreen : Screen()
    @Serializable
    data class  TaskScreen(val startMode: StartMode)
}