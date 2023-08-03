package com.trianglz.weatherapp.domain.utils

interface IUtilityManager {
    fun isInternetAvailable(): Boolean
    fun handleException(exception: Exception): String
}