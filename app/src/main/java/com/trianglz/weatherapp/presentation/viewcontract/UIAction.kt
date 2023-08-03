package com.trianglz.weatherapp.presentation.viewcontract

sealed class UIAction {
    data class Search(val query:String): UIAction()
    data class GetWeather(val countryCode: String, val limit: Int): UIAction()
}