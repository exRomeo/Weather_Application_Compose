package com.trianglz.weatherapp.domain.repository.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel

interface CountriesRepository {

    suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel>

}