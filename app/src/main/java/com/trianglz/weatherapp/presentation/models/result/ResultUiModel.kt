package com.trianglz.weatherapp.presentation.models.result

data class ResultUiModel(
    override val name: String,
    val code: String,
    val flag: String
) : ResultItem