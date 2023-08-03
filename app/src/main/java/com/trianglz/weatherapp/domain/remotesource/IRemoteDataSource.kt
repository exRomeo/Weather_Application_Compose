package com.trianglz.weatherapp.domain.remotesource

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

interface IRemoteDataSource {
    suspend fun getCountries(
        countryName: String
    ): List<Country>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City>

    suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): Weather


    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather
}