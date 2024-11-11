package com.example.simpletodo.features.core.presentation.nav

import com.example.simpletodo.features.core.presentation.screens.task.vm.StartMode
import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable
    data object MainScreen : Screens()
    @Serializable
    data class  TaskScreen(val startMode: StartMode)
}