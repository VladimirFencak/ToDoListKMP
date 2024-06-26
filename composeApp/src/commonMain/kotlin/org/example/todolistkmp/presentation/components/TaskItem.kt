package org.example.todolistkmp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import org.example.todolistkmp.domain.model.Task

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onTaskClick: (Task) -> Unit,
    onCheckboxClick: (Task) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable { onTaskClick(task) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            RadioButton(
                modifier = Modifier.padding(top = 4.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.onSecondary,
                    unselectedColor = MaterialTheme.colors.onSecondary
                ),
                selected = task.isCompleted,
                onClick = { onCheckboxClick(task) }
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .padding(end = 16.dp)
            ) {

                Text(
                    text = task.title,
                    color = MaterialTheme.colors.onSecondary,
                    style = MaterialTheme.typography.h6,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = task.description,
                    color = MaterialTheme.colors.onSecondary,
                    maxLines = 2,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}