package com.trianglz.weatherapp.domain.usecases.fetchcities

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel

interface FetchCitiesUseCase {

    /**
     * [getCities] just calls the method from the repository and wraps it in [runCatching] block
     * which returns one of two case success with the received data or failure with the Exception
     * */

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDomainModel, List<CityDataModel>>
}