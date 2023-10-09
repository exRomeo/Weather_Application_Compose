package com.trianglz.weatherapp.domain.repository.weatherdetails

import arrow.core.Either
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel

interface WeatherDetailsRepository {

    suspend fun oneCall(
        weather: WeatherDomainModel,
        lang: String,
        unit: String,
        exclude: String
    ): Either<WeatherAppErrorDomainModel, WeatherDetailsDomainModel>

}