package com.example.beechang.foodyfoodyfoody.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class FoodyResult(
    @Json(name = "aggregateLikes")
    val aggregateLikes: Int,
    @Json(name = "cheap")
    val cheap: Boolean,
    @Json(name = "dairyFree")
    val dairyFree: Boolean,
    @Json(name = "extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>,
    @Json(name = "glutenFree")
    val glutenFree: Boolean,
    @Json(name = "id")
    val recipeId: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "readyInMinutes")
    val readyInMinutes: Int,
    @Json(name = "sourceName")
    val sourceName: String?,
    @Json(name = "sourceUrl")
    val sourceUrl: String,
    @Json(name = "summary")
    val summary: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "vegan")
    val vegan: Boolean,
    @Json(name = "vegetarian")
    val vegetarian: Boolean,
    @Json(name = "veryHealthy")
    val veryHealthy: Boolean,
) : Parcelable


