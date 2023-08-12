package com.example.beechang.foodyfoodyfoody.model

sealed class ApiException(message: String) : Throwable(message) {
    class TokenExpired(message: String) : ApiException(message)
    class NotFound(message: String) : ApiException(message)
    class ServerError(message: String) : ApiException(message)
    class OthersError(message: String) : ApiException(message)
}