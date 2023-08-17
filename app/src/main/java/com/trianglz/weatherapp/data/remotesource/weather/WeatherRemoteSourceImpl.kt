package com.trianglz.weatherapp.data.remotesource.weather

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import javax.inject.Inject
import javax.inject.Named

class WeatherRemoteSourceImpl @Inject constructor(
    @Named("ApiKey")
    private val apiKey: String,
    private val apiNinja: ApiNinja
) : WeatherRemoteSource {

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDataModel =
        apiNinja.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )
}