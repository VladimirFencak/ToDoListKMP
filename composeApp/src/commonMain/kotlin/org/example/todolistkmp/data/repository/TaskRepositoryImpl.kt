package org.example.todolistkmp.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.todolistkmp.data.local.LocalData
import org.example.todolistkmp.data.remote.TaskApi
import org.example.todolistkmp.data.remote.dto.toTask
import org.example.todolistkmp.domain.errors.NetworkError
import org.example.todolistkmp.domain.errors.Result
import org.example.todolistkmp.domain.errors.RoomError
import org.example.todolistkmp.domain.model.Task
import org.example.todolistkmp.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskApi: TaskApi,
    private val localData: LocalData
) : TaskRepository {
    override fun getTaskById(taskId: Int): Result<Flow<Task?>, RoomError> {
        return try {
            Result.Success(localData.getTaskById(taskId))
        } catch (e: Exception) {
            Result.Error(getRoomException(e))
        }
    }

    override suspend fun createTask(task: Task): Result<Unit, RoomError> {
        return try {
            localData.insertTask(task)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(getRoomException(e))
        }
    }

    override suspend fun updateTask(task: Task): Result<Unit, RoomError> {
        return try {
            localData.updateTask(task)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(getRoomException(e))
        }
    }

    override suspend fun deleteTask(task: Task): Result<Unit, RoomError> {
        return try {
            localData.deleteTask(task)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(getRoomException(e))
        }
    }

    override fun getTasksOrderedByDateCreated(): Result<Flow<List<Task>>, RoomError> {
        return try {
            Result.Success(localData.getTasksOrderedByDateCreated())
        } catch (e: Exception) {
            Result.Error(getRoomException(e))
        }
    }

    override suspend fun getRemoteTasks(): Result<List<Task>, NetworkError> {
        taskApi.getTasks().also { result ->
            return when (result) {
                is Result.Success -> {
                    Result.Success(result.data.map { it.toTask() })
                }

                is Result.Error -> {
                    Result.Error(result.error)
                }
            }
        }
    }

    private fun getRoomException(exception: Exception): RoomError {
        return when (exception) {
            is IllegalArgumentException -> RoomError.ILLEGAL_ARGUMENT
            is IllegalStateException -> RoomError.ILLEGAL_STATE
            else -> RoomError.UNKNOWN_ERROR
        }
    }
}