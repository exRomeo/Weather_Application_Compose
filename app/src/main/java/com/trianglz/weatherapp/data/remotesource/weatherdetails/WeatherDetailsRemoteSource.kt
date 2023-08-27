package com.trianglz.weatherapp.data.remotesource.weatherdetails

import com.trianglz.weatherapp.data.models.weatherdetails.WeatherDetailsDataModel

interface WeatherDetailsRemoteSource {
    /**
     * [oneCall] retrieves all necessary data for the weather details screen from the API
     * check [api docs](https://openweathermap.org/appid) for more info
     */
    suspend fun oneCall(
        lat: Double,
        lon: Double,
        lang: String,
        unit: String,
        exclude: String
    ): WeatherDetailsDataModel
}