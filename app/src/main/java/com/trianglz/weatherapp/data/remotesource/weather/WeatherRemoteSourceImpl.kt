package com.trianglz.weatherapp.data.remotesource.weather

import com.trianglz.weatherapp.data.apiservice.weather.WeatherAPI
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
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
    ): WeatherDataModel =
        weatherApi.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )
}