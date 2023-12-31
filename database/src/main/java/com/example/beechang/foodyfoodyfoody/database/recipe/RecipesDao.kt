package com.example.beechang.foodyfoodyfoody.database.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.beechang.foodyfoodyfoody.database.DBDao
import com.example.beechang.foodyfoodyfoody.database.entity.FavoritesEntity
import com.example.beechang.foodyfoodyfoody.database.entity.RecipesEntity

@Dao
interface RecipesDao : DBDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    suspend fun readRecipes(): List<RecipesEntity>

    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    suspend fun readFavoriteRecipes(): List<FavoritesEntity>

    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()

}