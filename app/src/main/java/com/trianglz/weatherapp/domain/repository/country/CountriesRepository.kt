package com.trianglz.weatherapp.domain.repository.country

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

interface CountriesRepository {

    suspend fun getCountries(
        countryName: String
    ): List<CountryDomainModel>

}