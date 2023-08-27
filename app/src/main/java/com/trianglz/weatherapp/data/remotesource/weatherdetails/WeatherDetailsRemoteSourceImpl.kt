package com.trianglz.weatherapp.data.remotesource.weatherdetails

import com.trianglz.weatherapp.data.apiservice.weatherdetails.WeatherDetailsAPI
import com.trianglz.weatherapp.data.models.weatherdetails.WeatherDetailsDataModel
import javax.inject.Inject
import javax.inject.Named

class WeatherDetailsRemoteSourceImpl @Inject constructor(
    @Named("OpenWeatherMapApiKey")
    private val apiKey: String,
    private val weatherDetailsAPI: WeatherDetailsAPI
) : WeatherDetailsRemoteSource {


    override suspend fun oneCall(
        lat: Double, lon: Double, lang: String, unit: String,
        exclude: String
    ): WeatherDetailsDataModel =
        weatherDetailsAPI.oneCall(lat, lon, lang, unit, exclude, apiKey)
}
