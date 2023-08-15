package com.trianglz.weatherapp.data.remotesource

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface RemoteDataSource {
    suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel>

    suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): WeatherDataModel

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDataModel
}