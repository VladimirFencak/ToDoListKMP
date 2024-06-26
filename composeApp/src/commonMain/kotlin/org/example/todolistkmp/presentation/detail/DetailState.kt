package org.example.todolistkmp.presentation.detail

import org.example.todolistkmp.domain.errors.ErrorDefault
import org.example.todolistkmp.domain.model.Task

data class DetailState(
    val task: Task? = null,
    val formattedDate: String = "",
    val isLoading: Boolean = false,
    val error: ErrorDefault? = null,
    val showDeleteDialog: Boolean = false
)