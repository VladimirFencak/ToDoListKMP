package org.example.todolistkmp.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.example.todolistkmp.domain.errors.Result
import org.example.todolistkmp.domain.repository.TaskRepository

class DetailViewModel(
    private val taskRepository: TaskRepository,
    private val taskId: Int
) : ViewModel() {
    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            taskRepository.getTaskById(taskId).also { result ->
                when (result) {
                    is Result.Success -> {
                        result.data.collect { task ->
                            task?.let {
                                _state.value = _state.value.copy(
                                    isLoading = false,
                                    task = task,
                                    formattedDate = epochSecondsToReadableDate(task.createdAt)
                                )
                            }
                        }
                    }

                    is Result.Error -> {
                        _state.value = _state.value.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.DeleteTask -> {
                viewModelScope.launch {
                    state.value.task?.let { taskRepository.deleteTask(it) }
                }
            }

            DetailEvent.OnTaskCompletionChange -> {
                viewModelScope.launch {
                    state.value.task?.let { taskRepository.updateTask(it.copy(isCompleted = !it.isCompleted)) }
                }
            }

            DetailEvent.HideDeleteDialog -> {
                _state.value = _state.value.copy(showDeleteDialog = false)
            }

            DetailEvent.ShowDeleteDialog -> {
                _state.value = _state.value.copy(showDeleteDialog = true)
            }
        }
    }

    private fun epochSecondsToReadableDate(epochMillis: Long): String {
        val instant = Instant.fromEpochMilliseconds(epochMillis)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return "${localDateTime.dayOfMonth}.${localDateTime.monthNumber}.${localDateTime.year} ${localDateTime.hour}:${localDateTime.minute}"
    }
}