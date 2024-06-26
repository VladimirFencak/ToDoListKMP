package org.example.todolistkmp.data.local.sql

import database.TaskEntity
import org.example.todolistkmp.domain.model.Task

fun TaskEntity.toTask(
): Task {
    return Task(
        id = id.toInt(),
        title = title,
        description = description ?: "",
        isCompleted = isCompleted == 1L,
        createdAt = createdAt
    )
}

fun Task.toTaskEntity(
): TaskEntity {
    return TaskEntity(
        id = id.toLong(),
        title = title,
        description = description,
        isCompleted = if (isCompleted) 1L else 0L,
        createdAt = createdAt
    )
}
