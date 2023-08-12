package com.example.beechang.foodyfoodyfoody.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.beechang.foodyfoodyfoody.model.FoodRecipe
import com.example.beechang.foodyfoodyfoody.model.Result

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
 import javax.inject.Inject

@ProvidedTypeConverter
class TypeResponseConverter @Inject constructor(
    moshi: Moshi
) {

    private val foodRecipeAdapter: JsonAdapter<FoodRecipe> = moshi.adapter(FoodRecipe::class.java)
    private val resultAdapter: JsonAdapter<Result> = moshi.adapter(Result::class.java)

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return foodRecipeAdapter.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe {
        return foodRecipeAdapter.fromJson(data)!!
    }

    @TypeConverter
    fun resultToString(result: Result): String {
        return resultAdapter.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result {
        return resultAdapter.fromJson(data)!!
    }
}