package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

interface FetchCountriesUseCase {
    suspend fun getCountries(
        countryName: String
    ): Result<List<CountryDomainModel>>
}