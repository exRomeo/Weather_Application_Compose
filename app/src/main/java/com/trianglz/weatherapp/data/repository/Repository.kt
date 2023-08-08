package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather
import com.trianglz.weatherapp.domain.remotesource.IRemoteDataSource
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import com.trianglz.weatherapp.domain.utils.resource.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataSource: IRemoteDataSource,
    private val utilityManager: IUtilityManager
) : IRepository {

    override suspend fun getCountries(
        countryName: String
    ): Resource<List<Country>> =
        if (utilityManager.isInternetAvailable())
            try {
                Resource.Success(
                    dataSource.getCountries(
                        countryName = countryName
                    )
                )
            } catch (exception: Exception) {
                Resource.Error(utilityManager.handleException(exception))
            }
        else Resource.Error("Please, check your internet connection")


    private suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        )


    private suspend fun getWeather(
        city: City
    ): Weather? {
        return try {
            dataSource
                .getWeather(
                    latitude = city.latitude,
                    longitude = city.longitude
                ).apply {
                    cityName = city.name
                    countryCode = city.country
                }
        } catch (exception: Exception) {
            exception.printStackTrace()
            null
        }
    }

    override suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Resource<List<Weather>> = coroutineScope {
        if (utilityManager.isInternetAvailable())
            try {
                val list = getCities(countryCode, limit).map {
                    async {
                        getWeather(it)
                    }
                }.awaitAll().mapNotNull { it }.toList()

                if (list.isEmpty())
                    Resource.Error("This Country Has No Weather Data")
                else
                    Resource.Success(list)

            } catch (exception: Exception) {
                Resource.Error(utilityManager.handleException(exception))
            }
        else Resource.Error("Please, check your internet connection")
    }
}