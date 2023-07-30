package com.trianglz.weatherapp.data.datasource

import com.trianglz.weatherapp.core.apiservice.apininja.NinjaApi
import com.trianglz.weatherapp.core.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

class RemoteDataSource(
    private val ninjaApi: NinjaApi,
    private val apiKey: String,
    private val restCountriesAPI: RestCountriesAPI
) :
    IRemoteDataSource {

    override suspend fun getCountries(
        countryName: String,
        limit: Int
    ): List<Country> =
        restCountriesAPI.getCountries(
            countryName = countryName
        )

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City> =
        ninjaApi.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )

    override suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): Weather =
        ninjaApi.getWeather(
            apiKey = apiKey,
            cityName = cityName,
            countryCode = countryCode
        )

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather =
        ninjaApi.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )

}