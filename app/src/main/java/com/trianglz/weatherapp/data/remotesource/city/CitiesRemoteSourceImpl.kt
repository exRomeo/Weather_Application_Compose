package com.trianglz.weatherapp.data.remotesource.city

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.models.city.CityDataModel
import javax.inject.Inject
import javax.inject.Named

class CitiesRemoteSourceImpl @Inject constructor(
    @Named("ApiKey")
    private val apiKey: String,
    private val apiNinja: ApiNinja
) : CitiesRemoteSource {
    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel> =
        apiNinja.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )

}