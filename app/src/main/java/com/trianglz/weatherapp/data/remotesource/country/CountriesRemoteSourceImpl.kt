package com.trianglz.weatherapp.data.remotesource.country

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.resolve
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.data.retrofit.apiservice.countries.CountryAPI
import javax.inject.Inject

class CountriesRemoteSourceImpl @Inject constructor(
    private val countryAPI: CountryAPI
) : CountriesRemoteSource {
    override suspend fun getCountries(
        countryName: String
    ): Either<WeatherAppErrorDataModel, List<CountryDataModel>> = Either.catch {
        countryAPI.getCountries(
            countryName = countryName
        )
    }.mapLeft { resolve(it) }
}