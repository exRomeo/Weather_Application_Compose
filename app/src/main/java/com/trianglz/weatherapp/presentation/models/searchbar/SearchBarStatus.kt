package com.trianglz.weatherapp.presentation.models.searchbar

sealed class SearchBarStatus {
    object Loading : SearchBarStatus()
    object Error : SearchBarStatus()
    object Idle : SearchBarStatus()
}
