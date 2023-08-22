package com.example.beechang.foodyfoodyfoody.data.mapper

import com.example.beechang.foodyfoodyfoody.database.entity.FavoritesEntity
import com.example.beechang.foodyfoodyfoody.database.mapper.FavoriteEntityMapper
import com.example.beechang.foodyfoodyfoody.model.ExtendedIngredient
import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.example.beechang.foodyfoodyfoody.model.ui.ExtendedIngredientUiModel

object ExtendedIngredientUiMapper : UiMapper<ExtendedIngredient, ExtendedIngredientUiModel> {
    override fun asUi(data: ExtendedIngredient): ExtendedIngredientUiModel {
        return ExtendedIngredientUiModel(
            amount = data.amount,
            consistency = data.consistency,
            image = data.image,
            name = data.name,
            original = data.original,
            unit = data.unit
        )
    }
}

fun ExtendedIngredient.asUi(): ExtendedIngredientUiModel = ExtendedIngredientUiMapper.asUi(this)
