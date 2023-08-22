package com.example.beechang.foodyfoodyfoody.model.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize

data class ExtendedIngredientUiModel(
    val amount: Double,
    val consistency: String,
    val image: String,
    val name: String,
    val original: String,
    val unit: String
) : Parcelable
