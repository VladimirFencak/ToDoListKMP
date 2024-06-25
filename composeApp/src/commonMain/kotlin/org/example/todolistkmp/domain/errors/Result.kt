package org.example.todolistkmp.domain.errors

sealed interface Result<out D, out E : ErrorDefault> {
    data class Success<out D, out E : ErrorDefault>(val data: D) : Result<D, E>
    data class Error<out D, out E : ErrorDefault>(val error: E) : Result<D, E>
}