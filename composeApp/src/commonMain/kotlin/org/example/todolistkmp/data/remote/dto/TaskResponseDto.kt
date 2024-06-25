package org.example.todolistkmp.data.remote.dto

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import org.example.todolistkmp.domain.model.Task

@Serializable
data class TaskResponseDto(
    val id: Int,
    val title: String,
    val description: String?,
    val completed: Boolean,
    val createdAt: String
)

fun TaskResponseDto.toTask() = Task(
    id = id,
    title = title,
    description = description ?: "",
    isCompleted = completed,
    createdAt = Instant.parse(createdAt).toEpochMilliseconds()
)

fun Task.toTaskResponseDto() = TaskResponseDto(
    id = id,
    title = title,
    description = description,
    completed = isCompleted,
    createdAt = Instant.fromEpochMilliseconds(createdAt).toString()
)