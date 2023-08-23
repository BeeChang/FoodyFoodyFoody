package com.example.beechang.foodyfoodyfoody.detail


import com.example.beechang.foodyfoodyfoody.base.BaseViewModel
import com.example.beechang.foodyfoodyfoody.domain.CreateFavoriteRecipeUseCase
import com.example.beechang.foodyfoodyfoody.domain.DeleteFavoriteRecipeUseCase
import com.example.beechang.foodyfoodyfoody.domain.GetFavoriteRecipeUseCase
import com.example.beechang.foodyfoodyfoody.model.ui.FavoritesUiModel
import com.example.beechang.foodyfoodyfoody.recipe.RecipesViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase ,
    private val createFavoriteRecipeUseCase: CreateFavoriteRecipeUseCase ,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase ,
    ) : BaseViewModel() {

    private val _favorite = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val favoriteState: StateFlow<FavoriteUiState> = _favorite.asStateFlow()

    suspend fun checkFavoriteRecipe() {
        getFavoriteRecipeUseCase.invoke()
            .handleErrors {  errorMassage ->
                _favorite.update { FavoriteUiState.ShowError(errorMassage) }
            }
            .collect { favorites ->
                _favorite.update {
                    FavoriteUiState.checkFavoriteRecipes(favorites)
                }
            }
    }

    suspend fun insertFavoriteRecipe(favorites: FavoritesUiModel) =
        createFavoriteRecipeUseCase(favorites)

    suspend fun deleteFavoriteRecipe(favorites: FavoritesUiModel) =
        deleteFavoriteRecipeUseCase(favorites)


    sealed class FavoriteUiState {
        data class checkFavoriteRecipes(val favorites: List<FavoritesUiModel>) : FavoriteUiState()
        object Loading : FavoriteUiState()
        data class ShowError(val errorMassage: String) : FavoriteUiState()

    }
}