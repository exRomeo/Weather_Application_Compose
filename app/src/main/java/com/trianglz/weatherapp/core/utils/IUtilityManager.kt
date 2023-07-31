package com.trianglz.weatherapp.core.utils

interface IUtilityManager {
    fun isInternetAvailable(): Boolean
    fun handleException(throwable: Throwable): String
}