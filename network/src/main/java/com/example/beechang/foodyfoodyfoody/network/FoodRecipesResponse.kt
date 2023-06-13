package com.example.beechang.foodyfoodyfoody.network

import com.example.beechang.foodyfoodyfoody.model.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FoodRecipesResponse(
    @field:Json(name = "number")
    val number: Int,
    @field:Json(name = "offset")
    val offset: Int,
    @field:Json(name = "results")
    val results: List<Result>,
    @field:Json(name = "totalResults")
    val totalResults: Int
)

