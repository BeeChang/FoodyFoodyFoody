package com.example.beechang.foodyfoodyfoody.favorite

import androidx.lifecycle.viewModelScope
import com.example.beechang.foodyfoodyfoody.base.BaseViewModel
import com.example.beechang.foodyfoodyfoody.data.repository.FavoriteRepository
import com.example.beechang.foodyfoodyfoody.model.Favorites
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : BaseViewModel() {

    private val _favorite = MutableStateFlow<FavoriteUiState>(FavoriteUiState.FavoriteEmpty)
    val favoriteState: StateFlow<FavoriteUiState> = _favorite.asStateFlow()

     suspend fun readFavoriteRecipes() {
        favoriteRepository.selectFavoriteRecipes()
            .map { favorites ->
                if (favorites.isEmpty()){
                    FavoriteUiState.FavoriteEmpty
                } else {
                    FavoriteUiState.GetFavorite(favorites)
                }
            }
            .collect { favorites ->
                _favorite.update { favorites }
            }
    }

    fun deleteFavoriteRecipe(favorites: Favorites) {
        viewModelScope.launch {
            favoriteRepository.deleteFavoriteRecipes(favorites)
            readFavoriteRecipes()
        }
    }

    sealed class FavoriteUiState {
        object FavoriteEmpty : FavoriteUiState()
        data class GetFavorite(val favorites: List<Favorites>) : FavoriteUiState()
     }

}