package com.trianglz.weatherapp.data.remotesource

import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.data.models.country.CountryDto
import com.trianglz.weatherapp.data.models.weather.WeatherDto

interface IRemoteDataSource {
    suspend fun getCountries(
        countryName: String
    ): List<CountryDto>

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDto>

    suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): WeatherDto


    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDto
}