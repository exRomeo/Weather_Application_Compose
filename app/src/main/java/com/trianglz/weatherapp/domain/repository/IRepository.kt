package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

interface IRepository {
    suspend fun getCountries(
        countryName: String
    ): List<Country>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City>

    suspend fun getWeather(
        city: City
    ): Weather?

}