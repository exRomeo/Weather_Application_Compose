package com.trianglz.weatherapp.domain.repository.weather

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

interface WeatherRepository {

    suspend fun getWeather(
        city: CityDataModel
    ): Either<WeatherAppErrorDomainModel, WeatherDomainModel>

}