package com.example.beechang.foodyfoodyfoody.data.repository

 import com.example.beechang.foodyfoodyfoody.model.FoodyResult
 import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(
        searchWord : String = "",
    ): Flow<List<FoodyResult>>

}