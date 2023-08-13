package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.data.models.country.CountryDto
import com.trianglz.weatherapp.data.models.weather.WeatherDto
import com.trianglz.weatherapp.data.remotesource.IRemoteDataSource
import com.trianglz.weatherapp.domain.repository.IRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: IRemoteDataSource
) : IRepository {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDto> =
        dataSource.getCountries(
            countryName = countryName
        )


    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDto> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        )


    override suspend fun getWeather(
        city: CityDto
    ): WeatherDto {
        return dataSource
            .getWeather(
                latitude = city.latitude,
                longitude = city.longitude
            )
    }
}