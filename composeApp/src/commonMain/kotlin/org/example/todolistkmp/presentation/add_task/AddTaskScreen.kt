package org.example.todolistkmp.presentation.add_task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import todolistkmp.composeapp.generated.resources.Res
import todolistkmp.composeapp.generated.resources.add_task
import todolistkmp.composeapp.generated.resources.cancel
import todolistkmp.composeapp.generated.resources.description
import todolistkmp.composeapp.generated.resources.error
import todolistkmp.composeapp.generated.resources.save
import todolistkmp.composeapp.generated.resources.title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    navController: NavController
) {
    val viewModel = koinInject<AddTaskViewModel>(
        parameters = { parametersOf(2) }
    )
    val state = viewModel.state.value

    LaunchedEffect(state.savedSuccessfully) {
        if (state.savedSuccessfully) navController.popBackStack()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(Res.string.add_task))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.cancel),
                        )
                    }
                },
                actions = {
                    TextButton(onClick = {
                        viewModel.onEvent(AddTaskEvent.OnAddTaskTask)
                    }) {
                        Text(
                            text = stringResource(Res.string.save),
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(scaffoldPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                ),
                value = state.title,
                onValueChange = { viewModel.onEvent(AddTaskEvent.OnTitleChange(it)) },
                placeholder = {
                    Text(
                        text = stringResource(Res.string.title),
                        color = if (state.isMissingTitleError) {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        }
                    )
                },
                trailingIcon = {
                    if (state.isMissingTitleError)
                        Icon(Icons.Filled.Info, stringResource(Res.string.error), tint = MaterialTheme.colorScheme.error)
                }
            )

            HorizontalDivider()

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.surface,
                ),
                value = state.description,
                onValueChange = { viewModel.onEvent(AddTaskEvent.OnDescriptionChange(it)) },
                placeholder = {
                    Text(
                        text = stringResource(Res.string.description),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
            )
        }
    }
}