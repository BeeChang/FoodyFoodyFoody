package com.example.beechang.foodyfoodyfoody.test

import com.example.beechang.foodyfoodyfoody.model.Favorites
import com.example.beechang.foodyfoodyfoody.model.Result


object MockData {

    fun mockFoodResult() = Result(
        10,
        false,
        false,
        emptyList(),
        false,
        1,
        "image",
        30,
        "sourceName",
        "sourceUrl",
        "summary",
        "title",
        false,
        false,
        false
    )

    fun mockResultList() = listOf(
        mockFoodResult()
    )
    fun mockFavorites() = Favorites(
        1, mockFoodResult()
    )

    fun mockJsonString(key : String , value : String ) = """{ "$key": "$value" }"""

    fun mockInvalidJsonString(key : String , value : String ) = """{ "$key": "$value" """

}