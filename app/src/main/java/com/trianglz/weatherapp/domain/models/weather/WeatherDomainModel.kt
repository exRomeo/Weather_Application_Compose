package com.trianglz.weatherapp.domain.models.weather

data class WeatherDomainModel(
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
)