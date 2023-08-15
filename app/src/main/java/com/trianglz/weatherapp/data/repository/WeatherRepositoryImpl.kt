package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : WeatherRepository {

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