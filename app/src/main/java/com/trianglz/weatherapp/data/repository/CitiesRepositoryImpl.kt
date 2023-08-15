package com.trianglz.weatherapp.data.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource
) : CitiesRepository {

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel> =
        dataSource.getCities(
            countryCode = countryCode,
            limit = limit
        )
}