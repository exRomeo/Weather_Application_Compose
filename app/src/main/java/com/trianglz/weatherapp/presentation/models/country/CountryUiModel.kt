package com.trianglz.weatherapp.presentation.models.country

import com.trianglz.weatherapp.presentation.models.resultitem.ResultItem

data class CountryUiModel(
    override val name: String,
    val code: String,
    val flag: String
) : ResultItem