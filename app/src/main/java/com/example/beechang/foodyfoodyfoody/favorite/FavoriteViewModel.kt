package com.example.beechang.foodyfoodyfoody.favorite

import androidx.lifecycle.viewModelScope
import com.example.beechang.foodyfoodyfoody.base.BaseViewModel
import com.example.beechang.foodyfoodyfoody.domain.DeleteFavoriteRecipeUseCase
import com.example.beechang.foodyfoodyfoody.domain.GetFavoriteRecipeUseCase
import com.example.beechang.foodyfoodyfoody.model.ui.FavoritesUiModel
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
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase ,
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase ,
) : BaseViewModel() {

    private val _favorite = MutableStateFlow<FavoriteUiState>(FavoriteUiState.FavoriteEmpty)
    val favoriteState: StateFlow<FavoriteUiState> = _favorite.asStateFlow()

     suspend fun readFavoriteRecipes() {
         getFavoriteRecipeUseCase.invoke()
            .map { favorites ->
                if (favorites.isEmpty()){
                    FavoriteUiState.FavoriteEmpty
                } else {
                    FavoriteUiState.GetFavorite(favorites)
                }
            }.handleErrors { errorMassage ->
                _favorite.update { FavoriteUiState.ShowError(errorMassage) }
            }
            .collect { favorites ->
                _favorite.update { favorites }
            }
    }

    fun deleteFavoriteRecipe(favorites: FavoritesUiModel) {
        viewModelScope.launch {
            deleteFavoriteRecipeUseCase(favorites)
            readFavoriteRecipes()
        }
    }

    sealed class FavoriteUiState {
        data class ShowError(val errorMassage: String) : FavoriteUiState()
        object FavoriteEmpty : FavoriteUiState()
        data class GetFavorite(val favorites: List<FavoritesUiModel>) : FavoriteUiState()
     }

}