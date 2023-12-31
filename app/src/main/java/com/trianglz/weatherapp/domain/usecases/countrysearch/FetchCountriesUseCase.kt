package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

interface FetchCountriesUseCase {

    /**
     * [getCountries] just calls the method from the repository and wraps it in [runCatching] block
     * which returns one of two case success with the received data or failure with the Exception
     * */

    suspend fun getCountries(
        countryName: String
    ): Result<List<CountryDomainModel>>
}