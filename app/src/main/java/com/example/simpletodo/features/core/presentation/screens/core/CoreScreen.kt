package com.example.simpletodo.features.core.presentation.screens.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodo.R
import com.example.simpletodo.features.core.presentation.components.Task
import com.example.simpletodo.features.utl.ui.VerticalSpacer

@Composable
fun CoreScreen(
    tasks: List<Task>,
    onTaskChange: (Task) -> Unit,
    onTaskClicked: () -> Unit,
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
                items(tasks) { task ->
                    Task(
                        modifier = Modifier.padding(vertical = 5.dp),
                        title = task.title,
                        contentHint = task.contentHint,
                        completed = task.completed,
                        onClick = onTaskClicked
                    )
                    { completed ->
                        onTaskChange(task.copy(completed = completed))
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

private val previewTask = Task(title = "Test title", contentHint = 100 times "test content hint ", false)

@Preview
@Composable
fun CoreScreenPreview() {
    val tasks = 8 times previewTask
    CoreScreen(tasks, {}, {}) { }
}

infix fun <E> Int.times(x: E): List<E> = List(this) {x}
infix fun Int.times(x: String): String = x.repeat(this)