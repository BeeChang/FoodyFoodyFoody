package com.example.beechang.foodyfoodyfoody.recipe

import androidx.lifecycle.viewModelScope
import com.example.beechang.foodyfoodyfoody.base.BaseViewModel
import com.example.beechang.foodyfoodyfoody.domain.GetFoodRecipesUseCase
import com.example.beechang.foodyfoodyfoody.model.ui.FoodyUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getFoodRecipesUseCase: GetFoodRecipesUseCase
) : BaseViewModel() {

    private val _recipeFlow = MutableStateFlow<RecipeUiState>(RecipeUiState.Loading)
    val recipesState: StateFlow<RecipeUiState> = _recipeFlow.asStateFlow()

    init {
        viewModelScope.launch {
            getRecipesSafeCall()
        }
    }

    private suspend fun getRecipesSafeCall() {

        getFoodRecipesUseCase.invoke().onStart {
            _recipeFlow.update { RecipeUiState.Loading }
        }.handleErrors { errorMassage ->
            _recipeFlow.update { RecipeUiState.ShowError(errorMassage) }
        }.collect { recipesList ->
            _recipeFlow.update { RecipeUiState.RecipesListUpdate(recipesList) }
        }
    }

    sealed class RecipeUiState {
        data class ShowError(val errorMassage: String) : RecipeUiState()
        data class RecipesListUpdate(val recipes: List<FoodyUiModel>) : RecipeUiState()
        object Loading : RecipeUiState()
    }
}

