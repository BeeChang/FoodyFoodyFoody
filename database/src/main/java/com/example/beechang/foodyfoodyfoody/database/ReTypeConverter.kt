package com.example.beechang.foodyfoodyfoody.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.beechang.foodyfoodyfoody.model.FoodRecipe
import com.example.beechang.foodyfoodyfoody.model.FoodyResult

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
 import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    moshi: Moshi
) {

    private val foodRecipeAdapter: JsonAdapter<FoodRecipe> = moshi.adapter(FoodRecipe::class.java)
    private val foodyResultAdapter: JsonAdapter<FoodyResult> = moshi.adapter(FoodyResult::class.java)

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return foodRecipeAdapter.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        return foodRecipeAdapter.fromJson(data)!!
    }

    @TypeConverter
    fun resultToString(foodyResult: FoodyResult): String {
        return foodyResultAdapter.toJson(foodyResult)
    }

    @TypeConverter
    fun stringToResult(data: String): FoodyResult {
        return foodyResultAdapter.fromJson(data)!!
    }
}