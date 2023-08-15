package com.trianglz.weatherapp.data.repository.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.remotesource.country.CountriesRemoteSource
import com.trianglz.weatherapp.domain.repository.country.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val dataSource: CountriesRemoteSource
) : CountriesRepository {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        dataSource.getCountries(
            countryName = countryName
        )

}