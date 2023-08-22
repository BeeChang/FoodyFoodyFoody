package com.example.beechang.foodyfoodyfoody.domain

import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepository
import com.example.beechang.foodyfoodyfoody.domain.mapper.asUiModel
import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.example.beechang.foodyfoodyfoody.model.ui.FavoritesUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteRecipeUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(): Flow<List<FavoritesUiModel>> =
        favoriteRepository.selectFavoriteRecipes()
            .map { favorites ->
                favorites.map { it.asUiModel() }
            }
}