package com.example.beechang.foodyfoodyfoody.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beechang.foodyfoodyfoody.model.Result

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)