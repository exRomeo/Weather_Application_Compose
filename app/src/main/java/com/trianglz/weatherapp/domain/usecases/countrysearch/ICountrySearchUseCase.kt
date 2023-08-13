package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.domain.models.country.Country

interface ICountrySearchUseCase {
    suspend fun getCountries(
        countryName: String
    ): Result<List<Country>>
}