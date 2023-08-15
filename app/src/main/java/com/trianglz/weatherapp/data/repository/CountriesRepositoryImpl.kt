package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.domain.repository.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : CountriesRepository {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        dataSource.getCountries(
            countryName = countryName
        )

}