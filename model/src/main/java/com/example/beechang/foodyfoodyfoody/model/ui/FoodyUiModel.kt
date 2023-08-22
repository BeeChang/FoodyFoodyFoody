package com.example.beechang.foodyfoodyfoody.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodyUiModel(
    val aggregateLikes: Int,
    val cheap: Boolean,
    val dairyFree: Boolean,
    val extendedIngredients: List<ExtendedIngredientUiModel>,
    val glutenFree: Boolean,
    val recipeId: Int,
    val image: String,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val summary: String,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
) : Parcelable