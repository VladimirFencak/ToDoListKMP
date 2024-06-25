package org.example.todolistkmp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.example.todolistkmp.domain.errors.NetworkError
import org.example.todolistkmp.domain.errors.Result
import org.example.todolistkmp.domain.errors.RoomError
import org.example.todolistkmp.domain.model.Task

interface TaskRepository {
    fun getTaskById(taskId: Int): Result<Flow<Task?>, RoomError>
    suspend fun createTask(task: Task): Result<Unit, RoomError>
    suspend fun updateTask(task: Task): Result<Unit, RoomError>
    suspend fun deleteTask(task: Task): Result<Unit, RoomError>
    fun getTasksOrderedByDateCreated(): Result<Flow<List<Task>>, RoomError>
    suspend fun getRemoteTasks(): Result<List<Task>, NetworkError>
}