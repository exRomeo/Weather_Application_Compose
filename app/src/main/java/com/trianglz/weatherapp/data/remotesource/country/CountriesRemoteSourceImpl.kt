package com.trianglz.weatherapp.data.remotesource.country

import com.trianglz.weatherapp.data.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import javax.inject.Inject

class CountriesRemoteSourceImpl @Inject constructor(
    private val restCountriesAPI: RestCountriesAPI
) : CountriesRemoteSource {
    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        restCountriesAPI.getCountries(
            countryName = countryName
        )
}