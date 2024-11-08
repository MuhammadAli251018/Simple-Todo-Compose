package com.example.simpletodo.features.utl.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed class ScreenWithLoadingIndicatorState {
    object LoadingState : ScreenWithLoadingIndicatorState()
    object NormalState : ScreenWithLoadingIndicatorState()
    data class ShowErrorMessage(val errorMessage: String) : ScreenWithLoadingIndicatorState()
}

data class ScreenSettings(
    val showSuccessMessage: Boolean
)

@Composable
fun ScreenWithLoadingIndicator(
    screenState: ScreenWithLoadingIndicatorState,
    screenSettings: ScreenSettings,
    content: @Composable () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()

        when(screenState) {
            // Here handle each state
            ScreenWithLoadingIndicatorState.LoadingState -> TODO()
            ScreenWithLoadingIndicatorState.NormalState -> TODO()
            is ScreenWithLoadingIndicatorState.ShowErrorMessage -> TODO()
        }

    }
}