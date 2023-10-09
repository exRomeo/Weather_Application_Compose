package com.trianglz.weatherapp.data.repository.weather

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.toDomainModel
import com.trianglz.weatherapp.data.mappers.weather.toDomainModel
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.remotesource.weather.WeatherRemoteSource
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.repository.weather.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: WeatherRemoteSource
) : WeatherRepository {

    override suspend fun getWeather(
        city: CityDataModel
    ): Either<WeatherAppErrorDomainModel, WeatherDomainModel> = dataSource
        .getWeather(
            latitude = city.latitude,
            longitude = city.longitude
        ).mapLeft { it.toDomainModel() }.map { it.toDomainModel(city) }

}