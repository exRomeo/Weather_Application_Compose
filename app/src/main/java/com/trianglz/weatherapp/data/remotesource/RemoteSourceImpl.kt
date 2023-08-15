package com.trianglz.weatherapp.data.remotesource

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import javax.inject.Inject
import javax.inject.Named

class RemoteSourceImpl @Inject constructor(
    @Named("ApiKey")
    private val apiKey: String,
    private val apiNinja: ApiNinja,
    private val restCountriesAPI: RestCountriesAPI
) :
    RemoteDataSource {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDataModel> =
        restCountriesAPI.getCountries(
            countryName = countryName
        )

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDataModel> =
        apiNinja.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )

    override suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): WeatherDataModel =
        apiNinja.getWeather(
            apiKey = apiKey,
            cityName = cityName,
            countryCode = countryCode
        )

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDataModel =
        apiNinja.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )

}