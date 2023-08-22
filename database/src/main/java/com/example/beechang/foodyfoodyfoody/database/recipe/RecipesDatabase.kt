package com.example.beechang.foodyfoodyfoody.database.recipe

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.beechang.foodyfoodyfoody.database.entity.FavoritesEntity
import com.example.beechang.foodyfoodyfoody.database.entity.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class ],
    version = 2,
    exportSchema = false ,
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}