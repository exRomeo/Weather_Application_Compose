package com.trianglz.weatherapp.presentation.searchbarstate

sealed class SearchBarStatus {
    object Loading : SearchBarStatus()
    object Error : SearchBarStatus()
    object Idle : SearchBarStatus()
}
