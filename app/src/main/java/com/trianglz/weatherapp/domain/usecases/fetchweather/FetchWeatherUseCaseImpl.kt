package com.trianglz.weatherapp.domain.usecases.fetchweather

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class FetchWeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : FetchWeatherUseCase {
    override suspend fun getWeather(
        city: CityDataModel
    ): Result<WeatherDomainModel> =
        runCatching {
            weatherRepository
                .getWeather(city)
        }
}