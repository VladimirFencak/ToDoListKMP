package org.example.todolistkmp.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.example.todolistkmp.data.remote.dto.TaskResponseDto
import org.example.todolistkmp.domain.errors.NetworkError
import org.example.todolistkmp.domain.errors.Result

class TaskApiImpl(
    private val client: HttpClient
) : TaskApi {
    override suspend fun getTasks(): Result<List<TaskResponseDto>, NetworkError> {
        return try {
            val response = client.get(HttpRoutes.TASKS)

            if (response.status == HttpStatusCode.OK) Result.Success(response.body())
            else Result.Error(getErrorType(response))
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun <T> handleException(exception: Exception): Result<T, NetworkError> {
        return when (exception) {
            is ClientRequestException -> Result.Error(getErrorType(exception.response))
            is ServerResponseException -> Result.Error(getErrorType(exception.response))
            is ConnectTimeoutException -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            else -> Result.Error(NetworkError.UNKNOWN_ERROR)
        }
    }

    private fun getErrorType(response: HttpResponse): NetworkError {
        return when (response.status) {
            HttpStatusCode.Unauthorized -> NetworkError.UNAUTHORIZED
            HttpStatusCode.Forbidden -> NetworkError.FORBIDDEN
            HttpStatusCode.NotFound -> NetworkError.NOT_FOUND
            HttpStatusCode.InternalServerError -> NetworkError.INTERNAL_SERVER_ERROR
            else -> NetworkError.UNKNOWN_ERROR
        }
    }
}