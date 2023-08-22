package com.example.beechang.foodyfoodyfoody.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.beechang.foodyfoodyfoody.database.FAVORITE_RECIPES_TABLE
import com.example.beechang.foodyfoodyfoody.model.FoodyResult

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var foodyResult: FoodyResult
)