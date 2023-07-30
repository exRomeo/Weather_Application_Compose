package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

interface IRepository {
    suspend fun getCountries(
        countryName: String,
        limit: Int
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