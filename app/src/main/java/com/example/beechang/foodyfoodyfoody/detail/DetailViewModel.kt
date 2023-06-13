package com.example.beechang.foodyfoodyfoody.detail


import com.example.beechang.foodyfoodyfoody.base.BaseViewModel
import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepository
import com.example.beechang.foodyfoodyfoody.model.Favorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
    ) : BaseViewModel() {

    private val _favorite = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val favoriteState: StateFlow<FavoriteUiState> = _favorite.asStateFlow()

    suspend fun checkFavoriteRecipe() {
        favoriteRepository.selectFavoriteRecipes()
            .collect { favorites ->
                _favorite.update {
                    FavoriteUiState.checkFavoriteRecipes(favorites)
                }
            }
    }

    suspend fun insertFavoriteRecipe(favorites: Favorites) =
        favoriteRepository.insertFavoriteRecipes(favorites)

    suspend fun deleteFavoriteRecipe(favorites: Favorites) =
        favoriteRepository.deleteFavoriteRecipes(favorites)


    sealed class FavoriteUiState {
        data class checkFavoriteRecipes(val favorites: List<Favorites>) : FavoriteUiState()
        object Loading : FavoriteUiState()
    }
}