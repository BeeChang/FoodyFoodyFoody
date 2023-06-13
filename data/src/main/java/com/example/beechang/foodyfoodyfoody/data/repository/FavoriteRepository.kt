package com.example.beechang.foodyfoodyfoody.data.repository

 import com.example.beechang.foodyfoodyfoody.model.Favorites
 import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun insertFavoriteRecipes(favorites: Favorites)

    suspend fun deleteFavoriteRecipes(favorites: Favorites)

    suspend fun selectFavoriteRecipes() : Flow<List<Favorites>>
}