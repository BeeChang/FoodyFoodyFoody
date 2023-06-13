package com.example.beechang.foodyfoodyfoody.data


import com.example.beechang.foodyfoodyfoody.model.DataResult
import com.example.beechang.foodyfoodyfoody.model.ResultError
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

internal suspend fun <T> ApiResponse<T>.asResult(): DataResult<T> {
    return when (this) {
        is ApiResponse.Success -> {
            DataResult.Success(data)
        }

        is ApiResponse.Failure.Error -> {
            withContext(NonCancellable) {
                when (response.code()) {
                    in 401..402 -> DataResult.Fail( "auth error" ,  ResultError.TokenExpired)
                    404 -> DataResult.Fail("not found" , ResultError.NotFound)
                    in 500..599 -> DataResult.Fail( "server error" , ResultError.ServerError)
                    else -> DataResult.Fail( "unknown error" , ResultError.OthersError)
                }
             }
        }

        is ApiResponse.Failure.Exception -> {
            withContext(NonCancellable) {
                DataResult.Fail("network error" , exception)
            }
        }

    }
}