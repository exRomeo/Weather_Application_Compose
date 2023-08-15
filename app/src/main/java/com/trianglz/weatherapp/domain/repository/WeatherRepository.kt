package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface WeatherRepository {

    suspend fun getWeather(
        city: CityDataModel
    ): WeatherDataModel

}