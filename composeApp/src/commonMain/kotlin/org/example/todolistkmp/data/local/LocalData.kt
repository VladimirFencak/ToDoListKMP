package org.example.todolistkmp.data.local

import kotlinx.coroutines.flow.Flow
import org.example.todolistkmp.domain.model.Task

interface LocalData {
    fun getTaskById(taskId: Int): Flow<Task?>
    suspend fun insertTask(task: Task)
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    fun getTasksOrderedByDateCreated(): Flow<List<Task>>
}