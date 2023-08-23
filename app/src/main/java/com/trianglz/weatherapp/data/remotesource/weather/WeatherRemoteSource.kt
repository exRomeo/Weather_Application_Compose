package com.trianglz.weatherapp.data.remotesource.weather

import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface WeatherRemoteSource {

    /**
     * [getWeather] fetches the weather data from the api by specifying the city's [longitude] and [latitude]
     * */

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDataModel
}