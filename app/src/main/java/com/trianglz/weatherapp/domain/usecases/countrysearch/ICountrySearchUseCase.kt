package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.domain.models.country.Country
import com.trianglz.weatherapp.domain.utils.resource.Resource

interface ICountrySearchUseCase {
    suspend fun getCountries(
        countryName: String
    ): Resource<List<Country>>
}