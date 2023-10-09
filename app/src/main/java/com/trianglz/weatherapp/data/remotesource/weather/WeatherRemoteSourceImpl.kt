package com.trianglz.weatherapp.data.remotesource.weather

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.resolve
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import com.trianglz.weatherapp.data.retrofit.apiservice.weather.WeatherAPI
import javax.inject.Inject
import javax.inject.Named

class WeatherRemoteSourceImpl @Inject constructor(
    @Named("ApiNinjaApiKey")
    private val apiKey: String,
    private val weatherApi: WeatherAPI
) : WeatherRemoteSource {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Either<WeatherAppErrorDataModel, WeatherDataModel> = Either.catch {
        weatherApi.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )
    }.mapLeft { resolve(it) }
}