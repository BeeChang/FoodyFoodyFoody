package com.example.beechang.foodyfoodyfoody.data.repository

 import com.example.beechang.foodyfoodyfoody.model.Result
 import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(
        searchWord : String = "",
    ): Flow<List<Result>>

}