package com.example.simpletodo.features.core.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task(
    modifier: Modifier = Modifier,
    title: String,
    contentHint: String,
    completed: Boolean,
    onClick: () -> Unit,
    onCompleteStateChange: (Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            . fillMaxWidth()
            .clickable(onClick= onClick)
            .animateContentSize(
                animationSpec = tween(
                    delayMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    modifier = Modifier.padding(start = 10.dp, end = 15.dp),
                    checked = completed,
                    onCheckedChange = onCompleteStateChange
                )

                Text(
                    modifier = Modifier
                        .clickable{expanded = !expanded},
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
        completed = false,
        onClick = {}
    ) { }
}