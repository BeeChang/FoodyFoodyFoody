package com.example.beechang.foodyfoodyfoody.model

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Fail(val message : String , val error: Throwable) : DataResult<Nothing>()

    val isSuccess: Boolean
        get() = this is Success<T>

    val getData: T?
        get() = if (this is Success<T>) this.data else null

    val getFail : Fail?
        get() = if (this is Fail) this else null
}

sealed class ResultError : Throwable() {
    object TokenExpired : ResultError()
    object NotFound : ResultError()
    object ServerError : ResultError()
    object OthersError : ResultError()
}
