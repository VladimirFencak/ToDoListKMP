package org.example.todolistkmp.presentation.add_task

sealed class AddTaskEvent {
    data class OnTitleChange(val title: String) : AddTaskEvent()
    data class OnDescriptionChange(val description: String) : AddTaskEvent()
    data object OnAddTaskTask : AddTaskEvent()
}