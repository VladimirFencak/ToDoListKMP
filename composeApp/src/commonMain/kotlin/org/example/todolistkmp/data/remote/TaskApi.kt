package org.example.todolistkmp.data.remote

import org.example.todolistkmp.data.remote.dto.TaskResponseDto
import org.example.todolistkmp.domain.errors.NetworkError
import org.example.todolistkmp.domain.errors.Result

interface TaskApi {
    suspend fun getTasks(): Result<List<TaskResponseDto>, NetworkError>
}