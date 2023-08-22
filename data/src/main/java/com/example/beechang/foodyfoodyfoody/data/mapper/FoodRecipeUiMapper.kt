package com.example.beechang.foodyfoodyfoody.data.mapper

import com.example.beechang.foodyfoodyfoody.model.FoodyResult
import com.example.beechang.foodyfoodyfoody.model.ui.FoodyUiModel

object FoodRecipeUiMapper : UiMapper<FoodyResult, FoodyUiModel> {
    override fun asUi(data: FoodyResult): FoodyUiModel {
        return FoodyUiModel(
            aggregateLikes = data.aggregateLikes,
            cheap = data.cheap,
            dairyFree = data.dairyFree,
            extendedIngredients = data.extendedIngredients.map { it.asUi() },
            glutenFree = data.glutenFree,
            recipeId = data.recipeId,
            image = data.image,
            readyInMinutes = data.readyInMinutes,
            sourceName = data.sourceName,
            sourceUrl = data.sourceUrl,
            summary = data.summary,
            title = data.title,
            vegan = data.vegan,
            vegetarian = data.vegetarian,
            veryHealthy = data.veryHealthy
        )
    }
}


fun FoodyResult.asUi(): FoodyUiModel = FoodRecipeUiMapper.asUi(this)
