package com.trianglz.weatherapp.presentation.extensions

import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import kotlin.random.Random

fun WeatherDomainModel.getWeatherDescription(): String = when {
    currentTemperature >= 30.0 -> "Hot."
    currentTemperature >= 20.0 -> "Warm."
    currentTemperature >= 10.0 -> "Cool."
    currentTemperature >= 0.0 -> "Freezing!"
    else -> "Stay indoors."
}

fun WeatherDomainModel.getWeatherIcon(): Int {
    return when (Random.nextInt(1, 6)) {
        1 -> R.drawable.moon_cloud_fast_wind
        2 -> R.drawable.sun_cloud_angled_rain
        3 -> R.drawable.moon_cloud_mid_rain
        4 -> R.drawable.sun_cloud_mid_rain
        else -> R.drawable.tornado
    }
}