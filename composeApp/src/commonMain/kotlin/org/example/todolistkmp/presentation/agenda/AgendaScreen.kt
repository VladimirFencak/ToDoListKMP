package org.example.todolistkmp.presentation.agenda

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import org.example.todolistkmp.presentation.components.TaskItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import todolistkmp.composeapp.generated.resources.Res
import todolistkmp.composeapp.generated.resources.add_task
import todolistkmp.composeapp.generated.resources.agenda
import todolistkmp.composeapp.generated.resources.back
import todolistkmp.composeapp.generated.resources.profile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendaScreen(
    navController: NavController
) {
    val viewModel = koinInject<AgendaViewModel>()
    val state = viewModel.state.value
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(Res.string.agenda))
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.AccountCircle,
                            contentDescription = stringResource(Res.string.profile),
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.Menu,
                            contentDescription = stringResource(Res.string.back),
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("add_task_screen")
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, stringResource(Res.string.add_task))
            }
        }
    ) { scaffoldPadding ->
        if (state.isLoading) {
            LinearProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(scaffoldPadding)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
            ) {
                items(state.tasks) { task ->
                    TaskItem(
                        task = task,
                        onTaskClick = {
                            navController.navigate("detail_screen/${task.id}")
                        },
                        onCheckboxClick = { viewModel.onEvent(AgendaEvent.OnTaskCompletionChange(task)) }
                    )
                }
            }
        }

    }
}