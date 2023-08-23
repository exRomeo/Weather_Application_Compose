package com.trianglz.weatherapp.data.remotesource.country

import com.trianglz.weatherapp.data.apiservice.countries.CountryAPI
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import javax.inject.Inject

class CountriesRemoteSourceImpl @Inject constructor(
    private val countryAPI: CountryAPI
) : CountriesRemoteSource {
    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        countryAPI.getCountries(
            countryName = countryName
        )
}