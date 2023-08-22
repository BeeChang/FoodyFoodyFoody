package com.example.beechang.foodyfoodyfoody.data.mapper

interface UiMapper<Data, Ui> {
    fun asUi(data: Data): Ui
}
