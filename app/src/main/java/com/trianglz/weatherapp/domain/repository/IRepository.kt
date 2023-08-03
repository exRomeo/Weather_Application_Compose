package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.domain.utils.resource.Resource
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

interface IRepository {
    suspend fun getCountries(
        countryName: String
    ): Resource<List<Country>>

    suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Resource<List<Weather>>
}