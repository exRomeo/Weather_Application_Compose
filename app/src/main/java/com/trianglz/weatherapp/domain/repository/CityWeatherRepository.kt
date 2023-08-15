package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface CityWeatherRepository {

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel>

    suspend fun getWeather(
        city: CityDataModel
    ): WeatherDataModel
}