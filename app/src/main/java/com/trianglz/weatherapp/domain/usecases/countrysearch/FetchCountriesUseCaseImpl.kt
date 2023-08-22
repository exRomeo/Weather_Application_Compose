package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.repository.country.CountriesRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class FetchCountriesUseCaseImpl @Inject constructor(
    private val repository: CountriesRepository
) : FetchCountriesUseCase {
    override suspend fun getCountries(
        countryName: String
    ): Result<List<CountryDomainModel>> = coroutineScope {
        runCatching {
            repository.getCountries(
                countryName = countryName
            )
        }
    }
}