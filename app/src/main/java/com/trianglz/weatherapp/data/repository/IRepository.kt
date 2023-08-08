package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.utils.resource.Resource

interface IRepository {
    suspend fun getCountries(
        countryName: String
    ): Resource<List<Country>>

    suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Resource<List<Weather>>
}