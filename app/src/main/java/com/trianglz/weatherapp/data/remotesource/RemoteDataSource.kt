package com.trianglz.weatherapp.data.remotesource

import com.trianglz.weatherapp.domain.remotesource.IRemoteDataSource
import com.trianglz.weatherapp.data.remotesource.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.remotesource.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.data.models.weather.Weather

class RemoteDataSource(
    private val apiNinja: ApiNinja,
    private val apiKey: String,
    private val restCountriesAPI: RestCountriesAPI
) :
    IRemoteDataSource {

    override suspend fun getCountries(
        countryName: String
    ): List<Country> =
        restCountriesAPI.getCountries(
            countryName = countryName
        )

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<City> =
        apiNinja.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )

    override suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): Weather =
        apiNinja.getWeather(
            apiKey = apiKey,
            cityName = cityName,
            countryCode = countryCode
        )

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Weather =
        apiNinja.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )

}