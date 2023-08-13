package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.data.remotesource.IRemoteDataSource
import com.trianglz.weatherapp.domain.repository.IRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IRepository {

    override suspend fun getCountries(
        countryName: String
    ): List<Country> =
        dataSource.getCountries(
            countryName = countryName
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
        city: City
    ): Weather {
        return dataSource
            .getWeather(
                latitude = city.latitude,
                longitude = city.longitude
            ).apply {
                cityName = city.name
                countryCode = city.country
            }
    }
}