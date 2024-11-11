package com.example.simpletodo.features.core.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.simpletodo.features.core.presentation.screens.main.MainScreen
import com.example.simpletodo.features.core.presentation.screens.main.vm.MainScreenVm
import com.example.simpletodo.features.core.presentation.screens.task.TaskScreen
import com.example.simpletodo.features.core.presentation.screens.task.vm.TaskScreenVm
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CoreFeatureNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: Screen = Screen.MainScreen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Screen.MainScreen> {
            val viewModel = koinViewModel<MainScreenVm>()
            MainScreen(stateHandler = viewModel)
        }
        composable<Screen.TaskScreen> { backStackEntry ->
            val startMode = backStackEntry.toRoute<Screen.TaskScreen>().startMode
            val viewModel = koinViewModel<TaskScreenVm>(parameters = { parametersOf(startMode) })
            TaskScreen(stateHandler = viewModel)
        }
    }

}