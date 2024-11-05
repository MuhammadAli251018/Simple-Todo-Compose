package com.example.simpletodo.features.core.presentation.screens.task

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.simpletodo.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpletodo.features.utl.collectPropertyAsState
import com.example.simpletodo.features.utl.ui.VerticalSpacer

@Composable
fun TaskScreen(
    stateHandler: TaskStateHandler,
    toCoreScreen: () -> Unit
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
        onSaveButtonClick = stateHandler::onSaveButton,
        onDeleteButtonClick = stateHandler::onDeleteButtonClick,
        outOfScreen = toCoreScreen
    )
}

@Composable
fun TaskScreen(
    title: String,
    content: String,
    state: Boolean,
    onTitleChange: (newTitle: String) -> Unit,
    onContentChange: (newContent: String) -> Unit,
    onStateChange: (newState: Boolean) -> Unit,
    onSaveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    outOfScreen: () -> Unit
) {
    Box(
        modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  Task Title TaskField And Task State
            VerticalSpacer(15.dp)

            Box (
                modifier = Modifier.fillMaxWidth(),
            ) {

                Checkbox(
                    modifier = Modifier.align(Alignment.CenterStart),
                    checked = state,
                    onCheckedChange = onStateChange
                )

                TextField(
                    modifier = Modifier.align(Alignment.Center),
                    value = title,
                    onValueChange = onTitleChange,
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        errorLabelColor = Color.Transparent,
                        focusedLabelColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    textStyle = TextStyle.Default.copy(
                        fontSize = 28.sp,
                        color = TextStyle.Default.color.copy(alpha = .5f),
                        textDecoration = if (state) TextDecoration.LineThrough else TextDecoration.None
                    )
                )

                IconButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 15.dp),
                    onClick = onDeleteButtonClick
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete_24),
                        contentDescription = "Delete Task"
                    )
                }
            }

            //  Task Content TaskField
            VerticalSpacer(25.dp)

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = content,
                onValueChange = onContentChange,
                colors = TextFieldDefaults.colors(
                    errorLabelColor = Color.Transparent,
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle.Default.copy(
                    fontSize = 18.sp,
                    color = TextStyle.Default.color.copy(alpha = .8f)
                )
            )

        }

        // Save Task Button
        FloatingActionButton(
            modifier = Modifier
                .padding(15.dp)
                .size(80.dp)
                .align(alignment = Alignment.BottomEnd),
            shape = CircleShape,
            content = {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_done_24
                    ),
                    contentDescription = "Icon"
                )
            },
            onClick = {
                onSaveButtonClick()
                outOfScreen()
            }
        )
    }
}

private fun getPreviewStateHandler(): TaskStateHandler {
    return object : TaskStateHandler {
        private val _state = MutableStateFlow(TaskState(
            title = "Test Title",
            content = "Test Content",
            state = true,
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

        override fun onStateChange(newState: Boolean) {
            updateState {
                this.copy(
                    state = newState
                )
            }
        }

        override fun onSaveButton() = Unit
        override fun onDeleteButtonClick() = Unit
    }
}

@Preview(showBackground = true)
@Composable
fun TaskScreenPreview(
) {
    val stateHandler = getPreviewStateHandler()
    TaskScreen(stateHandler) {}
}