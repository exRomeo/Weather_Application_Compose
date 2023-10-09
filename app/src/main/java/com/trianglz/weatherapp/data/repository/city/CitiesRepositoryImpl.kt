package com.trianglz.weatherapp.data.repository.city

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.toDomainModel
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.remotesource.city.CitiesRemoteSource
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.repository.city.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val dataSource: CitiesRemoteSource
) : CitiesRepository {

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDomainModel, List<CityDataModel>> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        ).mapLeft { it.toDomainModel() }
}