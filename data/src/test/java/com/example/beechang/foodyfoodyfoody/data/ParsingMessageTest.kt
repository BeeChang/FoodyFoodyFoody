package com.example.beechang.foodyfoodyfoody.data

import com.example.beechang.foodyfoodyfoody.test.MockData
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ParsingMessageTest {



    @Test
    fun `WHEN targetString is null EXPECT it should return empty string`() {
        val result = parsingMessage(null)
        assertEquals("", result)
    }

    @Test
    fun `WHEN targetString is a valid JSON with message EXPECT it should return the message value`() {
        val jsonValue = "Auth Error"
        val jsonString = MockData.mockJsonString("message" ,jsonValue)

        val result = parsingMessage(jsonString)

        assertEquals(jsonValue, result)
    }

    @Test
    fun `WHEN targetString is a valid JSON without message EXPECT it should return empty string`() {
        val jsonValue = "Something wrong"
        val jsonString = MockData.mockJsonString("otherKey" ,jsonValue)

        val result = parsingMessage(jsonString)

        assertEquals("", result)
    }

    @Test
    fun `WHEN targetString is an invalid JSON EXPECT it should return empty string`() {
        val jsonValue = "Invalid Json"
        val jsonString = MockData.mockInvalidJsonString("message" ,jsonValue)

        val result = parsingMessage(jsonString)

        assertEquals("", result)
    }
}