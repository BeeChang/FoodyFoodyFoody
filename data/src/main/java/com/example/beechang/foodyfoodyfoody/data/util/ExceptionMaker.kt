package com.example.beechang.foodyfoodyfoody.data

import com.example.beechang.foodyfoodyfoody.model.ApiException
import org.json.JSONObject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun provideErrorType(
    code: Int,
    errorBody: String?,
    message: String = "",
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): ApiException = withContext(dispatcher) {

    val resolvedMessage = message.ifEmpty {
        parsingMessage(errorBody)
    }

    when (code) {
        in 401..402 -> ApiException.TokenExpired(resolvedMessage)
        404 -> ApiException.NotFound(resolvedMessage)
        in 500..599 -> ApiException.ServerError(resolvedMessage)
        else -> ApiException.OthersError(resolvedMessage)
    }
}

fun parsingMessage( targetString: String?): String = if (targetString == null) {
    ""
} else {
    try {
        JSONObject(targetString).getString("message")
    } catch (e: Exception) {
        ""
    }
}