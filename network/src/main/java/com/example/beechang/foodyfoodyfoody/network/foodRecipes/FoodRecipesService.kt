package com.example.beechang.foodyfoodyfoody.network.foodRecipes

import com.example.beechang.foodyfoodyfoody.network.API_KEY
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodRecipesService {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @Query("query") search : String,
        @Query("number") number : String,
        @Query("type") type : String = "main course",
        @Query("addRecipeInformation") addRecipeInformation : String = "true",
        @Query("fillIngredients") fillIngredients : String = "true",
        @Query("apiKey") apiKey: String = API_KEY
    ): ApiResponse<FoodRecipesResponse>

}