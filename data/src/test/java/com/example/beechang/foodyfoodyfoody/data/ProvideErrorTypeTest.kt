package com.example.beechang.foodyfoodyfoody.data

import com.example.beechang.foodyfoodyfoody.model.ApiException
import com.example.beechang.foodyfoodyfoody.test.MockData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
@OptIn(ExperimentalCoroutinesApi::class)
class ProvideErrorTypeTest {

    @Test
    fun `WHEN given 401 code, empty message and valid JSON with message EXPECT return TokenExpired with message value`() =
        runTest {
            val code = 401
            val massage = ""
            val value = "test value"
            val errorBody = MockData.mockJsonString("message", value)

            val result = provideErrorType(code, errorBody, massage)

            assertTrue(result is ApiException.TokenExpired)
            assertEquals(value, result.message)
        }

    @Test
    fun `WHEN given 401 code and not empty message EXPECT return TokenExpired with message value`() =
        runTest {
            val code = 401
            val massage = "Exist 401"
            val value = "401 value"
            val errorBody = MockData.mockJsonString("message", value)

            val result = provideErrorType(code, errorBody, massage)

            assertTrue(result is ApiException.NotFound)
            assertEquals(massage, result.message)
            assertNotEquals(value, result.message)
        }


    @Test
    fun `WHEN given 404 code, empty message and valid JSON with message EXPECT return NotFound with message value`() =
        runTest {
            val code = 404
            val massage = ""
            val value = "404 value"
            val errorBody = MockData.mockJsonString("message", value)

            val result = provideErrorType(code, errorBody, massage)

            assertTrue(result is ApiException.NotFound)
            assertEquals(value, result.message)
        }


    @Test
    fun `WHEN given 500 code, empty message and valid JSON with message EXPECT return ServerError with message value`() =
        runTest {
            val code = 500
            val massage = ""
            val value = "500 value"
            val errorBody = MockData.mockJsonString("message", value)

            val result = provideErrorType(code, errorBody, massage)

            assertTrue(result is ApiException.ServerError)
            assertEquals(value, result.message)
        }

    @Test
    fun `WHEN given other number code, empty message and valid JSON with message EXPECT return OthersError with message value`() =
        runTest {
            val code = 999
            val massage = ""
            val value = "999 value"
            val errorBody = MockData.mockJsonString("message", value)

            val result = provideErrorType(code, errorBody, massage)

            assertTrue(result is ApiException.OthersError)
            assertEquals(value, result.message)
        }


}