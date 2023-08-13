package com.trianglz.weatherapp.domain.utils

interface IUtilityManager {
    fun handleException(exception: Exception): String
}