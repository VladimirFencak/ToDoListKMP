package org.example.todolistkmp.domain.errors

enum class NetworkError : ErrorDefault {
    REQUEST_TIMEOUT,
    NO_INTERNET,
    SERIALIZATION_ERROR,
    UNAUTHORIZED,           //401
    FORBIDDEN,              //403
    NOT_FOUND,              //404
    INTERNAL_SERVER_ERROR,  //500
    UNKNOWN_ERROR
}