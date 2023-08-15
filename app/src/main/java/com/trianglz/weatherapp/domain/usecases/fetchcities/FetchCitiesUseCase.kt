package com.trianglz.weatherapp.domain.usecases.fetchcities

import com.trianglz.weatherapp.data.models.city.CityDataModel

interface FetchCitiesUseCase {
    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Result<List<CityDataModel>>
}