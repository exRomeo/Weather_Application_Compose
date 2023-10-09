package com.trianglz.weatherapp.data.repository.weatherdetails

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.toDomainModel
import com.trianglz.weatherapp.data.mappers.weatherdetails.toDomainModel
import com.trianglz.weatherapp.data.remotesource.weatherdetails.WeatherDetailsRemoteSource
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel
import com.trianglz.weatherapp.domain.repository.weatherdetails.WeatherDetailsRepository
import javax.inject.Inject

class WeatherDetailsRepositoryImpl @Inject constructor(
    private val weatherDetailsRemoteSource: WeatherDetailsRemoteSource
) : WeatherDetailsRepository {

    override suspend fun oneCall(
        weather: WeatherDomainModel,
        lang: String,
        unit: String,
        exclude: String
    ): Either<WeatherAppErrorDomainModel, WeatherDetailsDomainModel> = weatherDetailsRemoteSource
        .oneCall(
            weather.lat,
            weather.lon,
            lang,
            unit,
            exclude
        )
        .mapLeft { it.toDomainModel() }
        .map { it.toDomainModel(weather) }
}