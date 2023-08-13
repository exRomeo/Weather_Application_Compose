package com.trianglz.weatherapp.data.remotesource

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.data.models.country.CountryDto
import com.trianglz.weatherapp.data.models.weather.WeatherDto
import javax.inject.Inject
import javax.inject.Named

class RemoteDataSource @Inject constructor(
    @Named("ApiKey")
    private val apiKey: String,
    private val apiNinja: ApiNinja,
    private val restCountriesAPI: RestCountriesAPI
) :
    IRemoteDataSource {

    override suspend fun getCountries(
        countryName: String
    ): List<CountryDto> =
        restCountriesAPI.getCountries(
            countryName = countryName
        )

    override suspend fun getCities(
        countryCode: String,
        limit: Int
    ): List<CityDto> =
        apiNinja.getCities(
            apiKey = apiKey,
            countryCode = countryCode,
            limit = limit
        )

    override suspend fun getWeather(
        cityName: String,
        countryCode: String
    ): WeatherDto =
        apiNinja.getWeather(
            apiKey = apiKey,
            cityName = cityName,
            countryCode = countryCode
        )

    override suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): WeatherDto =
        apiNinja.getWeather(
            apiKey = apiKey,
            latitude = latitude,
            longitude = longitude
        )

}