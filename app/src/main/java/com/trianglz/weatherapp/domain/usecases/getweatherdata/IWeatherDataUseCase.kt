package com.trianglz.weatherapp.domain.usecases.getweatherdata

import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.utils.resource.Resource

interface IWeatherDataUseCase {
    suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Resource<List<Weather>>
}