package com.example.beechang.foodyfoodyfoody.domain

import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepository
import com.example.beechang.foodyfoodyfoody.domain.mapper.asDomain
import com.example.beechang.foodyfoodyfoody.model.ui.FavoritesUiModel
import javax.inject.Inject

class DeleteFavoriteRecipeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(favoritesUiModel: FavoritesUiModel) =
        favoriteRepository.deleteFavoriteRecipes(favoritesUiModel.asDomain())
}