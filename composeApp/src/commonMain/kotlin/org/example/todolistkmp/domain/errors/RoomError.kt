package org.example.todolistkmp.domain.errors

enum class RoomError : ErrorDefault {
    DATABASE_ERROR,
    ILLEGAL_STATE,
    ILLEGAL_ARGUMENT,
    UNKNOWN_ERROR
}