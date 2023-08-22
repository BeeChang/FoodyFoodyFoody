package com.example.beechang.foodyfoodyfoody.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beechang.foodyfoodyfoody.database.RECIPES_TABLE
import com.example.beechang.foodyfoodyfoody.model.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}