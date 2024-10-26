package com.example.simpletodo.features.core.presentation.screens.task

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpletodo.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import com.example.simpletodo.features.utl.collectPropertyAsState

@Composable
fun TaskScreen(
    stateHandler: TaskStateHandler
) {
    val title by stateHandler.state.collectPropertyAsState { title }
    val content by stateHandler.state.collectPropertyAsState { content }
    val state by stateHandler.state.collectPropertyAsState { state }

    TaskScreen(
        title = title,
        content = content,
        state = state,
        onTitleChange = stateHandler::onTitleChange,
        onContentChange = stateHandler::onContentChange,
        onStateChange = stateHandler::onStateChange,
        onSaveButton = stateHandler::onSaveButton,
    )
}

//Todo: Complete the screen use the state (Complete or not) and it's action
@Composable
fun TaskScreen(
    title: String,
    content: String,
    state: String,
    onTitleChange: (newTitle: String) -> Unit,
    onContentChange: (newContent: String) -> Unit,
    onStateChange: (newState: String) -> Unit,
    onSaveButton: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //  Task Title TaskField
        TextField(
            value = title,
            onValueChange = onTitleChange
        )

        //  Task Content TaskField
        TextField(
            value = content,
            onValueChange = onContentChange
        )

        // Save Task Button
        FloatingActionButton(
            shape = CircleShape,
            content = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_launcher_foreground
                    ),
                    contentDescription = "Icon"
                )
            },
            onClick = onSaveButton
        )
    }
}

private fun getPreviewStateHandler(): TaskStateHandler {
    return object : TaskStateHandler {
        private val _state = MutableStateFlow(TaskState(
            title = "Test Title",
            content = "Test Content",
            state = "Completed",
            issueTime = "1:15 AM Today"
        ))
        override val state: StateFlow<TaskState> = _state.asStateFlow()

        private fun updateState(block: TaskState.() -> TaskState) {
            _state.apply {
                value = block(value)
            }
        }

        override fun onTitleChange(newTitle: String) {
            updateState {
                this.copy(
                    title = newTitle
                )
            }
        }

        override fun onContentChange(newContent: String) {
            updateState {
                this.copy(
                    content = newContent
                )
            }
        }

        override fun onStateChange(newState: String) {
            updateState {
                this.copy(
                    state = newState
                )
            }
        }

        override fun onSaveButton() = Unit
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview(
) {
    val stateHandler = getPreviewStateHandler()
    TaskScreen(stateHandler)
}