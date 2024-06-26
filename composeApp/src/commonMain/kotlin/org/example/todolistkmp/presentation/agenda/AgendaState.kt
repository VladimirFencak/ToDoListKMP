package org.example.todolistkmp.presentation.agenda

import org.example.todolistkmp.domain.errors.ErrorDefault
import org.example.todolistkmp.domain.model.Task

data class AgendaState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false,
    val error: ErrorDefault? = null
)