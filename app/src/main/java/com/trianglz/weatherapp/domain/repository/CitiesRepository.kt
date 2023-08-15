package com.trianglz.weatherapp.domain.repository

import com.trianglz.weatherapp.data.models.city.CityDataModel

interface CitiesRepository {

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel>
}