package com.example.beechang.foodyfoodyfoody.network

import com.example.beechang.foodyfoodyfoody.network.foodRecipes.FoodRecipesResponse
import com.example.beechang.foodyfoodyfoody.network.foodRecipes.FoodRecipesService
import com.example.beechang.foodyfoodyfoody.test.MainDispatcherRule
import com.example.beechang.foodyfoodyfoody.test.MockData
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestScope
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FoodRecipesServiceTest {

    @get:Rule
    var coroutinesRule = MainDispatcherRule()

    private lateinit var service: FoodRecipesService
    private lateinit var mockWebServer: MockWebServer
    private lateinit var moshi : Moshi

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        mockWebServer = MockWebServer()

        moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(
                ApiResponseCallAdapterFactory.create(
                    coroutineScope = TestScope(coroutinesRule.testDispatcher)
                )
            )
            .build()
            .create(FoodRecipesService::class.java)
    }

    @After
    fun shutdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `WHEN getRecipes is called EXPECT to match the mock response`() = runBlocking {
        val expectedResults = MockData.mockResultList()
        val expectedResponse = FoodRecipesResponse(1, 0, expectedResults, 1)
        val jsonAdapter = moshi.adapter(FoodRecipesResponse::class.java)
        val jsonResponse = jsonAdapter.toJson(expectedResponse)
        val mockResponse = MockResponse()
            .setBody(jsonResponse)
            .setResponseCode(200)
        mockWebServer.enqueue(mockResponse)
        val response = service.getRecipes("", "1")
        val responseBody = requireNotNull((response as ApiResponse.Success).data)

        val mockResult = responseBody.foodyResults

        MatcherAssert.assertThat(mockResult[0].recipeId ,  Is.`is`(1))
        MatcherAssert.assertThat(mockResult[0].title ,  Is.`is`("title"))
    }

}