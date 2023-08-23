package com.trianglz.weatherapp.data.remotesource.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel

interface CountriesRemoteSource {

    /**
     * [getCountries] fetches a list of countries including all countries that match [countryName]
     * */

    suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel>

}