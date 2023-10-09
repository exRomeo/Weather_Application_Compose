package com.trianglz.weatherapp.data.remotesource.country

import arrow.core.Either
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel

interface CountriesRemoteSource {

    /**
     * [getCountries] fetches a list of countries including all countries that match [countryName]
     * */

    suspend fun getCountries(
        countryName: String
    ): Either<WeatherAppErrorDataModel, List<CountryDataModel>>

}