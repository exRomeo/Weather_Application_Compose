package com.trianglz.weatherapp.data.remotesource.weatherdetails

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.errors.resolve
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.data.models.weatherdetails.WeatherDetailsDataModel
import com.trianglz.weatherapp.data.retrofit.apiservice.weatherdetails.WeatherDetailsAPI
import javax.inject.Inject
import javax.inject.Named

class WeatherDetailsRemoteSourceImpl @Inject constructor(
    @Named("OpenWeatherMapApiKey")
    private val apiKey: String,
    private val weatherDetailsAPI: WeatherDetailsAPI
) : WeatherDetailsRemoteSource {


    override suspend fun oneCall(
        lat: Double, lon: Double, lang: String, unit: String,
        exclude: String
    ): Either<WeatherAppErrorDataModel, WeatherDetailsDataModel> = Either.catch {
        weatherDetailsAPI.oneCall(lat, lon, lang, unit, exclude, apiKey)
    }.mapLeft {
        resolve(it)
    }
}