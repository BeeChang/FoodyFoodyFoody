package com.example.beechang.foodyfoodyfoody.data.repository

import com.example.beechang.foodyfoodyfoody.database.recipe.RecipesDao
import com.example.beechang.foodyfoodyfoody.database.mapper.asDomain
import com.example.beechang.foodyfoodyfoody.database.mapper.asEntity
import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.example.beechang.foodyfoodyfoody.network.Dispatcher
import com.example.beechang.foodyfoodyfoody.network.FoodyDispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val recipesDao: RecipesDao,
    @Dispatcher(FoodyDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : FavoriteRepository {


    override suspend fun insertFavoriteRecipes(favorites: Favorites) {
        recipesDao.insertFavoriteRecipe(favorites.asEntity())
    }

    override suspend fun deleteFavoriteRecipes(favorites: Favorites) {
        recipesDao.deleteFavoriteRecipe(favorites.asEntity())
    }

    override suspend fun selectFavoriteRecipes(): Flow<List<Favorites>> = flow {
        val favoritesEntityList = recipesDao.readFavoriteRecipes()
        emit(favoritesEntityList.map { it.asDomain() })
    }.flowOn(ioDispatcher)
}