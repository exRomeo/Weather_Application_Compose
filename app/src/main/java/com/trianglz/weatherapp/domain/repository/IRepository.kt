package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.data.models.country.CountryDto
import com.trianglz.weatherapp.data.models.weather.WeatherDto

interface IRepository {
    suspend fun getCountries(
        countryName: String
    ): List<CountryDto>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDto>

    suspend fun getWeather(
        city: CityDto
    ): WeatherDto

}