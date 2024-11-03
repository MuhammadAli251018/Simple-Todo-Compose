package com.example.simpletodo.features.core.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task(
    title: String,
    contentHint: String,
    expanded: Boolean,
    completed: Boolean,
    onCompleteStateChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            . fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    modifier = Modifier.align(alignment = Alignment.CenterStart),
                    checked = completed,
                    onCheckedChange = onCompleteStateChange
                )

                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.Center),
                            text= title,
                            style =  TextStyle.Default.copy(fontSize = 20.sp)
                )
            }

            if (expanded)
                Text(
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                    text= contentHint,
                    style =  TextStyle.Default.copy(fontSize = 16.sp)
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskPreview() {
    Task(
        title = "Test Title",
        contentHint = "some bla here, there. Zoroth, Marthsome bla here, there. Zoroth, Marthsome bla here, there. Zoroth, Marthsome bla here, there. Zoroth, Marthsome bla here, there. Zoroth, Marth.",
        expanded = true,
        completed = false
    ) { }
}