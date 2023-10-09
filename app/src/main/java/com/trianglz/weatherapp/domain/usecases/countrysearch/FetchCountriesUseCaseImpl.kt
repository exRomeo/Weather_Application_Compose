package com.trianglz.weatherapp.domain.usecases.countrysearch

import arrow.core.Either
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.repository.country.CountriesRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class FetchCountriesUseCaseImpl @Inject constructor(
    private val repository: CountriesRepository
) : FetchCountriesUseCase {
    override suspend fun getCountries(
        countryName: String
    ): Either<WeatherAppErrorDomainModel, List<CountryDomainModel>> = coroutineScope {
        repository.getCountries(
            countryName = countryName
        )

    }
}