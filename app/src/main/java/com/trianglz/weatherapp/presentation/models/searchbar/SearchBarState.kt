package com.trianglz.weatherapp.presentation.models.searchbar

import com.trianglz.weatherapp.presentation.models.result.ResultState

data class SearchBarState(
    val placeHolder: String = "",
    val status: ResultState<Nothing> = ResultState.Idle(),
)