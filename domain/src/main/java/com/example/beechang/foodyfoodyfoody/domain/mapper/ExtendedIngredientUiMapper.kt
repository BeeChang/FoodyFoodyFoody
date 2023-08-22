package com.example.beechang.foodyfoodyfoody.domain.mapper

import com.example.beechang.foodyfoodyfoody.model.ExtendedIngredient
import com.example.beechang.foodyfoodyfoody.model.ui.ExtendedIngredientUiModel

internal fun ExtendedIngredient.asUiModel() = ExtendedIngredientUiModel(
    amount = this.amount,
    consistency = this.consistency,
    image = this.image,
    name = this.name,
    original = this.original,
    unit = this.unit
)