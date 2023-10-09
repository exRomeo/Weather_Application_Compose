package com.trianglz.weatherapp.domain.usecases.fetchweather

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

interface FetchWeatherUseCase {

    /**
     * [getWeather] just calls the method from the repository and wraps it in [runCatching] block
     * which returns one of two case success with the received data or failure with the Exception
     * */

    suspend fun getWeather(
        city: CityDataModel
    ): Either<WeatherAppErrorDomainModel, WeatherDomainModel>
}