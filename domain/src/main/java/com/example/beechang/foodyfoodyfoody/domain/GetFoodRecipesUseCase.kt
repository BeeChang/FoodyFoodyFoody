package com.example.beechang.foodyfoodyfoody.domain

import com.example.beechang.foodyfoodyfoody.data.repository.RecipesRepository
import com.example.beechang.foodyfoodyfoody.domain.mapper.asUiModel
import com.example.beechang.foodyfoodyfoody.model.ui.FoodyUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFoodRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(search: String = ""): Flow<List<FoodyUiModel>> =
        recipesRepository.getRecipes(search).map { foodResultList ->
            foodResultList.map { it.asUiModel() }
        }
}