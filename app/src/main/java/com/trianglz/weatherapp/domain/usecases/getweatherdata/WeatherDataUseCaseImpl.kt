package com.trianglz.weatherapp.domain.usecases.getweatherdata

import com.trianglz.weatherapp.data.mappers.weather.toDomainModel
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.repository.CityWeatherRepository
import com.trianglz.weatherapp.domain.utils.UtilityManager
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class WeatherDataUseCaseImpl @Inject constructor(
    private val repository: CityWeatherRepository,
    private val utilityManager: UtilityManager
) : WeatherDataUseCase {

    override suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Result<List<WeatherDomainModel>> = coroutineScope {
        try {
            val list = repository.getCities(countryCode, limit).map {
                async {
                    getWeather(it)
                }
            }.awaitAll().map { it }.toList()

            if (list.isEmpty())
                Result.failure(Exception("This Country Has No Weather Data"))
            else
                Result.success(list)

        } catch (exception: Exception) {
            Result.failure(Exception(utilityManager.handleException(exception)))
        }
    }

    private suspend fun getWeather(
        city: CityDataModel
    ): WeatherDomainModel =
        repository.getWeather(city).toDomainModel(city)
}