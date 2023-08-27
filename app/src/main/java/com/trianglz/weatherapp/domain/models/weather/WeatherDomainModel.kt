package com.trianglz.weatherapp.domain.models.weather

data class WeatherDomainModel(
    val countryCode: String,
    val cityName: String,
    val lat: Double,
    val lon: Double,
    val currentTemperature: Int,
    val lowTemperature: Int,
    val highTemperature: Int,
)