package com.trianglz.weatherapp.domain.usecases.fetchcities

import com.trianglz.weatherapp.data.models.city.CityDataModel

interface FetchCitiesUseCase {

    /**
     * [getCities] just calls the method from the repository and wraps it in [runCatching] block
     * which returns one of two case success with the received data or failure with the Exception
     * */

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Result<List<CityDataModel>>
}