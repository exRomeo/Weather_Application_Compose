package com.trianglz.weatherapp.domain.usecases.getweatherdata

import com.trianglz.weatherapp.domain.models.weather.Weather

interface IWeatherDataUseCase {
    suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Result<List<Weather>>
}