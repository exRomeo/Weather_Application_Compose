package com.trianglz.weatherapp.domain.repository.city

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel

interface CitiesRepository {

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDomainModel,List<CityDataModel>>
}