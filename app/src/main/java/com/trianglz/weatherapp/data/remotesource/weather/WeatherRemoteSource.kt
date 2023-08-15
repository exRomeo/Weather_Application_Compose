package com.trianglz.weatherapp.data.remotesource.weather

import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface WeatherRemoteSource {

    suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): WeatherDataModel

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDataModel
}