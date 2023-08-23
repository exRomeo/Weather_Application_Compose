package com.trianglz.weatherapp.presentation.models.searchbar

data class SearchBarState(
    val placeHolder: String = "",
    val status: SearchBarStatus = SearchBarStatus.Idle,
    val noResultMessage: String = "",
)