package com.trianglz.weatherapp.domain.models.weather

import com.trianglz.weatherapp.R
import kotlin.random.Random

data class Weather(
    val countryCode: String,
    val cityName: String,
    val currentTemperature: Int,
    val feelsLike: Int,
    val lowTemperature: Int,
    val highTemperature: Int,
    val humidityPercentage: Int,
    val cloudPercentage: Int,
    val windSpeed: Double,
    val windDegree: Int,
    val sunrise: Long,
    val sunset: Long,
) {
    fun getWeatherDescription(): String = when {
        currentTemperature >= 30.0 -> "Hot."
        currentTemperature >= 20.0 -> "Warm."
        currentTemperature >= 10.0 -> "Cool."
        currentTemperature >= 0.0 -> "Freezing!"
        else -> "Stay indoors."
    }

    fun getWeatherIcon(): Int {
        return when (Random.nextInt(1, 6)) {
            1 -> R.drawable.moon_cloud_fast_wind
            2 -> R.drawable.sun_cloud_angled_rain
            3 -> R.drawable.moon_cloud_mid_rain
            4 -> R.drawable.sun_cloud_mid_rain
            else -> R.drawable.tornado
        }
    }
}