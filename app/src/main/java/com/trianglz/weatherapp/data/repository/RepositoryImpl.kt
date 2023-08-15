package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : Repository {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        dataSource.getCountries(
            countryName = countryName
        )


    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        )


    override suspend fun getWeather(
        city: CityDataModel
    ): WeatherDataModel {
        return dataSource
            .getWeather(
                latitude = city.latitude,
                longitude = city.longitude
            )
    }
}