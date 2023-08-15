package com.trianglz.weatherapp.domain.utils

interface UtilityManager {
    fun handleException(exception: Exception): String
}