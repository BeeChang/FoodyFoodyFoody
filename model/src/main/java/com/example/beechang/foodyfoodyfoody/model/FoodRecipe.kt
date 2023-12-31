package com.example.beechang.foodyfoodyfoody.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class FoodRecipe(
    @Json(name = "results")
    val foodyResults: List<FoodyResult>
)
