package com.example.beechang.foodyfoodyfoody.domain.mapper

import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.example.beechang.foodyfoodyfoody.model.ui.FavoritesUiModel

internal fun Favorites.asUiModel() = FavoritesUiModel (
    id = this.id ,
    foodyUiModel = this.foodyResult.asUiModel()
)

internal fun FavoritesUiModel.asDomain() = Favorites (
    id = this.id ,
    foodyResult = this.foodyUiModel.asDomain()
)
