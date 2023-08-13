package com.trianglz.weatherapp.domain.usecases.getweatherdata

import com.trianglz.weatherapp.data.mappers.weather.toWeather
import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.domain.models.weather.Weather
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import com.trianglz.weatherapp.domain.utils.resource.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class WeatherDataUseCase @Inject constructor(
    private val repository: IRepository,
    private val utilityManager: IUtilityManager
) : IWeatherDataUseCase {

    override suspend fun getWeatherData(
        countryCode: String,
        limit: Int
    ): Resource<List<Weather>> = coroutineScope {
            try {
                val list = repository.getCities(countryCode, limit).map {
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
    }

    private suspend fun getWeather(city: CityDto): Weather =
        repository.getWeather(city).toWeather(city)
}