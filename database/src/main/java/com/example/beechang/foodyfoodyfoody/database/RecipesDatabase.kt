package com.example.beechang.foodyfoodyfoody.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class ],
    version = 2,
    exportSchema = false ,
)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}