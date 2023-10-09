package com.trianglz.weatherapp.data.remotesource.city

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.resolve
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.data.retrofit.apiservice.city.CityAPI
import javax.inject.Inject
import javax.inject.Named


class CitiesRemoteSourceImpl @Inject constructor(
    @Named("ApiNinjaApiKey")
    private val apiKey: String,
    private val cityAPI: CityAPI
) : CitiesRemoteSource {
    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDataModel, List<CityDataModel>> = Either.catch {
        cityAPI.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )
    }.mapLeft { resolve(it) }
}