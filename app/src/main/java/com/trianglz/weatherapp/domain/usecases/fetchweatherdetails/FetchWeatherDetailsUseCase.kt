package com.trianglz.weatherapp.domain.usecases.fetchweatherdetails

import arrow.core.Either
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel

interface FetchWeatherDetailsUseCase {

    /**
     * [getWeatherDetails] just calls the method from the repository and wraps it in [runCatching] block
     * which returns one of two case success with the received data or failure with the Exception
     * */

    suspend fun getWeatherDetails(
        weatherDomainModel: WeatherDomainModel,
        lang: String,
        unit: String,
        exclude: String
    ): Either<WeatherAppErrorDomainModel, WeatherDetailsDomainModel>

}