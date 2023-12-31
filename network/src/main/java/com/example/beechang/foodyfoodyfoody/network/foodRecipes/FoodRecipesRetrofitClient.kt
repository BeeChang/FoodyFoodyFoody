package com.example.beechang.foodyfoodyfoody.network.foodRecipes

import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class FoodRecipesRetrofitClient @Inject constructor(
    private val foodRecipesService: FoodRecipesService
) {
    suspend fun fetchFoodRecipes(
        search: String = "",
        number: String = "10",
        type: String = "main course",
        addRecipeInformation: String = "true",
        fillIngredients: String = "true"
    ): ApiResponse<FoodRecipesResponse> =
        foodRecipesService.getRecipes(search, number, type, addRecipeInformation, fillIngredients)

}