package com.trianglz.weatherapp.domain.usecases.getweatherdata

import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

interface WeatherDataUseCase {
    suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Result<List<WeatherDomainModel>>
}