package com.trianglz.weatherapp.core.apiservice

import com.trianglz.weatherapp.data.city.City
import com.trianglz.weatherapp.data.country.Country
import com.trianglz.weatherapp.data.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Query

interface NinjaApi {

    @GET("/country")
    suspend fun getCountries(
        @Query("name") countryName: String,
        @Query("limit") limit: Int
    ): List<Country>

    @GET("/city")
    suspend fun getCities(
        @Query("country") countryCode: String,
        @Query("limit") limit: Int
    ): List<City>

    @GET("weather")
    suspend fun getWeather(
        @Query("city") cityName: String,
        @Query("country") countryCode: String
    ): Weather


}