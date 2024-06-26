package org.example.todolistkmp.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.example.todolistkmp.domain.model.Task

@Entity
data class TaskEntity(
    val title: String,
    val description: String?,
    val isCompleted: Boolean,
    val createdAt: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

fun TaskEntity.toTask() = Task(
    id = id ?: 0,
    title = title,
    description = description ?: "",
    isCompleted = isCompleted,
    createdAt = createdAt
)

fun Task.toTaskEntity() = TaskEntity(
    id = id,
    title = title,
    description = description,
    isCompleted = isCompleted,
    createdAt = createdAt
)