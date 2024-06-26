package org.example.todolistkmp.presentation.add_task

data class AddTaskState(
    val title: String = "",
    val description: String = "",
    val isMissingTitleError: Boolean = false,
    val savedSuccessfully: Boolean = false
)