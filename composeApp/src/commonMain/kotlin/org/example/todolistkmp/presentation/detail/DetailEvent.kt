package org.example.todolistkmp.presentation.detail

sealed class DetailEvent {
    data object DeleteTask : DetailEvent()
    data object OnTaskCompletionChange : DetailEvent()
    data object ShowDeleteDialog : DetailEvent()
    data object HideDeleteDialog : DetailEvent()
}