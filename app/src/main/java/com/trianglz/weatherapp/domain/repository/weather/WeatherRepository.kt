package com.trianglz.weatherapp.domain.repository.weather

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

interface WeatherRepository {

    suspend fun getWeather(
        city: CityDataModel
    ): WeatherDomainModel

}