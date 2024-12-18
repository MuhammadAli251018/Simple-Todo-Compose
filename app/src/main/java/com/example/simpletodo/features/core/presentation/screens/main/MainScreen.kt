package com.example.simpletodo.features.core.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.simpletodo.R
import com.example.simpletodo.features.core.presentation.components.Task
import com.example.simpletodo.features.core.presentation.nav.Screen
import com.example.simpletodo.features.core.presentation.screens.task.vm.StartMode
import com.example.simpletodo.features.utl.ui.VerticalSpacer

@Composable
fun MainScreen(
    stateHandler: MainStateHandler,
) {
    val state by stateHandler.state.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    val toTaskScreen: (StartMode) -> Unit = remember {
        { startMode ->
            navController.navigate(Screen.TaskScreen(startMode))
        }
    }

    MainScreen(
        tasks =state.tasks ,
        onTaskChange = remember {  stateHandler::onTaskChange},
        onTaskClicked = {id, index -> toTaskScreen(StartMode.ViewTask(index.toLong()))},
        onNewTaskClick = {toTaskScreen(StartMode.NewTask)}
    )
}

@Composable
fun MainScreen(
    tasks: List<TaskItem>,
    onTaskChange: (Int,Int, TaskItem) -> Unit,
    onTaskClicked: (id: Int, index: Int) -> Unit,
    onNewTaskClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ){
        Column(modifier = Modifier.fillMaxWidth()) {

            //  Screen Title
            VerticalSpacer(15.dp)
            Text(
                text = "Tasks",
                style = TextStyle.Default.copy(fontSize = 20.sp)
            )

            // Tasks List
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 15.dp)
            ) {
                itemsIndexed(items = tasks, key = {_, item -> item.id}) { index, task ->
                    Task(
                        modifier = Modifier.padding(vertical = 5.dp),
                        title = task.title,
                        contentHint = task.contentHint,
                        completed = task.completed,
                        onClick = { onTaskClicked(task.id, index) }
                    )
                    { completed ->
                        onTaskChange(task.id, index, task.copy(completed = completed))
                    }
                }
            }
        }

        //  New Task Button
        FloatingActionButton(
            modifier = Modifier
                .padding(15.dp)
                .size(80.dp)
                .align(alignment = Alignment.BottomEnd),
            shape = CircleShape,
            content = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_add_24
                    ),
                    contentDescription = "Icon"
                )
            },
            onClick = onNewTaskClick
        )
    }
}

private val previewTask = TaskItem(id = 0, title = "Test title", contentHint = 100 times "test content hint ", false)

@Preview
@Composable
fun MainScreenPreview() {
    val tasks = 8 times previewTask
    MainScreen(tasks, {_,_,_ ->}, {_, _ -> }) { }
}

infix fun <E> Int.times(x: E): List<E> = List(this) {x}
infix fun Int.times(x: String): String = x.repeat(this)