package com.trianglz.weatherapp.domain.usecases.fetchweather

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

interface FetchWeatherUseCase {
    suspend fun getWeather(
        city: CityDataModel
    ): Result<WeatherDomainModel>
}