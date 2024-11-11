package com.example.simpletodo.features.core.presentation.nav

import com.example.simpletodo.features.core.presentation.screens.task.vm.StartMode

sealed class Screens {

    data object MainScreen : Screens()
    data class  TaskScreen(val startMode: StartMode)
}