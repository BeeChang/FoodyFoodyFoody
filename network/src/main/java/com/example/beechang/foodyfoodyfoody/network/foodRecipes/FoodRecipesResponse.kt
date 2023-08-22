package com.example.beechang.foodyfoodyfoody.network.foodRecipes

import com.example.beechang.foodyfoodyfoody.model.FoodyResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodRecipesResponse(
    @field:Json(name = "number")
    val number: Int,
    @field:Json(name = "offset")
    val offset: Int,
    @field:Json(name = "results")
    val foodyResults: List<FoodyResult>,
    @field:Json(name = "totalResults")
    val totalResults: Int
)

