package org.example.todolistkmp.presentation.agenda

import org.example.todolistkmp.domain.model.Task

sealed class AgendaEvent {
    data class OnTaskCompletionChange(val task: Task) : AgendaEvent()
    data class OnTaskDelete(val task: Task) : AgendaEvent()
}