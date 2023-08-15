package com.trianglz.weatherapp.data.remotesource.city

import com.trianglz.weatherapp.data.models.city.CityDataModel

interface CitiesRemoteSource {

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel>
}