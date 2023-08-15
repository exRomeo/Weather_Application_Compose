package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface Repository {
    suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel>

    suspend fun getWeather(
        city: CityDataModel
    ): WeatherDataModel

}