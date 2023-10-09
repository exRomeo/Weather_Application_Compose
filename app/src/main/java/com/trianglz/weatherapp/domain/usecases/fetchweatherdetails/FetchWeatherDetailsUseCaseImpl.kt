package com.trianglz.weatherapp.domain.usecases.fetchweatherdetails

import arrow.core.Either
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel
import com.trianglz.weatherapp.domain.repository.weatherdetails.WeatherDetailsRepository
import javax.inject.Inject

class FetchWeatherDetailsUseCaseImpl @Inject constructor(
    private val weatherDetailsRepository: WeatherDetailsRepository
) : FetchWeatherDetailsUseCase {

    override suspend fun getWeatherDetails(
        weatherDomainModel: WeatherDomainModel,
        lang: String,
        unit: String,
        exclude: String
    ): Either<WeatherAppErrorDomainModel, WeatherDetailsDomainModel> =
        weatherDetailsRepository.oneCall(
            weatherDomainModel,
            lang,
            unit,
            exclude
        )

}