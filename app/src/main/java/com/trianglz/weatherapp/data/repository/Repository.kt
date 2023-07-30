package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.datasource.IRemoteDataSource
import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

class Repository(private val dataSource: IRemoteDataSource) : IRepository {

    override suspend fun getCountries(
        countryName: String,
        limit: Int
    ): List<Country> =
        dataSource.getCountries(
            countryName = countryName,
            limit = limit
        )

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        )

    override suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): Weather =
        dataSource.getWeather(
            cityName = cityName,
            countryCode = countryCode
        )

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather =
        dataSource
            .getWeather(
                latitude = latitude,
                longitude = longitude
            )
}