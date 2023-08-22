package com.example.beechang.foodyfoodyfoody.domain.mapper

import com.example.beechang.foodyfoodyfoody.model.FoodyResult
import com.example.beechang.foodyfoodyfoody.model.ui.FoodyUiModel
internal fun FoodyResult.asUiModel() = FoodyUiModel(
    aggregateLikes = this.aggregateLikes,
    cheap = this.cheap,
    dairyFree = this.dairyFree,
    extendedIngredients = this.extendedIngredients.map { it.asUiModel() },
    glutenFree = this.glutenFree,
    recipeId = this.recipeId,
    image = this.image,
    readyInMinutes = this.readyInMinutes,
    sourceName = this.sourceName,
    sourceUrl = this.sourceUrl,
    summary = this.summary,
    title = this.title,
    vegan = this.vegan,
    vegetarian = this.vegetarian,
    veryHealthy = this.veryHealthy
)

internal fun FoodyUiModel.asDomain() = FoodyResult(
    aggregateLikes = this.aggregateLikes,
    cheap = this.cheap,
    dairyFree = this.dairyFree,
    extendedIngredients = this.extendedIngredients.map { it.asDomain() },
    glutenFree = this.glutenFree,
    recipeId = this.recipeId,
    image = this.image,
    readyInMinutes = this.readyInMinutes,
    sourceName = this.sourceName,
    sourceUrl = this.sourceUrl,
    summary = this.summary,
    title = this.title,
    vegan = this.vegan,
    vegetarian = this.vegetarian,
    veryHealthy = this.veryHealthy
)