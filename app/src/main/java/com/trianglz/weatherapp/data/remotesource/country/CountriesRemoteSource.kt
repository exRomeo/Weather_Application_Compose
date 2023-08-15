package com.trianglz.weatherapp.data.remotesource.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel

interface CountriesRemoteSource {

    suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel>

}