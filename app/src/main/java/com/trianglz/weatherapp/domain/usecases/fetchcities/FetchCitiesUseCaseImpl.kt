package com.trianglz.weatherapp.domain.usecases.fetchcities

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.repository.city.CitiesRepository
import javax.inject.Inject

class FetchCitiesUseCaseImpl @Inject constructor(
    private val citiesRepository: CitiesRepository
) : FetchCitiesUseCase {

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDomainModel, List<CityDataModel>> =
        citiesRepository
            .getCities(countryCode, limit)

}