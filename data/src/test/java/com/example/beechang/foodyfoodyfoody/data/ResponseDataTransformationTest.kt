package com.example.beechang.foodyfoodyfoody.data

import com.example.beechang.foodyfoodyfoody.model.DataResult
import com.example.beechang.foodyfoodyfoody.model.ResultError
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class ResponseDataTransformationTest{

    @Test
    fun `WHEN result of ApiResponse is Success EXPECT DataResult is Success`() = runTest {
        val data = "Success"
        val apiResponse = ApiResponse.Success(retrofit2.Response.success(data))

        val result = apiResponse.asResult()

        assertTrue(result is DataResult.Success)
        assertEquals(data, (result as DataResult.Success).data)
    }

    @Test
    fun `WHEN result of ApiResponse is http statusCode is 401 EXPECT the DataResult is Fail and the ResultError has TokenExpired`() = runTest {
        val message = "Auth Exception"
        val responseBody = message.toResponseBody("text/plain".toMediaTypeOrNull())
        val apiResponse = ApiResponse.Failure.Error<Response>(retrofit2.Response.error(401, responseBody))
        val result = apiResponse.asResult()

        assertTrue(result is DataResult.Fail)
        assertEquals(message, result.getFail?.message)
        assertTrue(result.getFail?.error is ResultError.TokenExpired)
    }

    @Test
    fun `WHEN result of ApiResponse is Failure Exception EXPECT the DataResult is Fail and the ResultError has received Exception`() = runTest {
        val exception = Exception("Test Exception")
        val apiResponse = ApiResponse.Failure.Exception<String>(exception)

        val result = apiResponse.asResult()

        assertTrue(result is DataResult.Fail)
        assertEquals("error", (result as DataResult.Fail).message)
        assertEquals(exception, result.error)
    }
}