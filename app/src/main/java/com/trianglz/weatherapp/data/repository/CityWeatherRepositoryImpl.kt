package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import com.trianglz.weatherapp.domain.repository.CitiesRepository
import com.trianglz.weatherapp.domain.repository.CityWeatherRepository
import com.trianglz.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class CityWeatherRepositoryImpl @Inject constructor(
    private val citiesRepository: CitiesRepository,
    private val weatherRepository: WeatherRepository
) : CityWeatherRepository {

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel> =
        citiesRepository
            .getCities(countryCode, limit)


    override suspend fun getWeather(
        city: CityDataModel
    ): WeatherDataModel =
        weatherRepository
            .getWeather(city)
}