package com.example.beechang.foodyfoodyfoody.data

import app.cash.turbine.test
import com.example.beechang.foodyfoodyfoody.data.repository.RecipesRepositoryImpl
import com.example.beechang.foodyfoodyfoody.network.foodRecipes.FoodRecipesResponse
import com.example.beechang.foodyfoodyfoody.network.foodRecipes.FoodRecipesRetrofitClient
import com.example.beechang.foodyfoodyfoody.network.foodRecipes.FoodRecipesService
import com.example.beechang.foodyfoodyfoody.test.MainDispatcherRule
import com.example.beechang.foodyfoodyfoody.test.MockData
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import retrofit2.Response
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@ExperimentalTime
class RecipesRepositoryImplTest {

    @get:Rule
    var coroutinesRule = MainDispatcherRule()
    private lateinit var recipesRepository: RecipesRepositoryImpl
    private lateinit var foodRecipesRetrofitClient: FoodRecipesRetrofitClient
    private val retrofitService: FoodRecipesService = mock()

    @Before
    fun setup() {
        foodRecipesRetrofitClient = FoodRecipesRetrofitClient(retrofitService)
        recipesRepository =
            RecipesRepositoryImpl(foodRecipesRetrofitClient, coroutinesRule.testDispatcher)
    }

    @Test
    fun `WHEN request a recipes data EXPECT correct Recipes data returned `() = runTest {
        val mockData = FoodRecipesResponse(1, 1, MockData.mockResultList(), 1)
        whenever(retrofitService.getRecipes("", "1")).thenReturn(ApiResponse.of {
            Response.success(mockData)
        })

        recipesRepository.getRecipes().test {
            val expectItem = awaitItem()[0]
            assertEquals(expectItem.recipeId, 1)
            assertEquals(expectItem.title, "title")
            awaitComplete()
        }

        verify(retrofitService, atLeastOnce()).getRecipes("" , "1")
        verifyNoMoreInteractions(retrofitService)
    }

}