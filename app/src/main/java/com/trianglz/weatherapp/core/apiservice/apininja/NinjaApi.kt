package com.trianglz.weatherapp.core.apiservice.apininja

import com.trianglz.weatherapp.data.models.city.City
import com.trianglz.weatherapp.data.models.weather.Weather
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NinjaApi {

    @GET("city/")
    suspend fun getCities(
        @Header("X-Api-Key") apiKey: String,
        @Query("country") countryCode: String,
        @Query("limit") limit: Int
    ): List<City>

    @GET("weather/")
    suspend fun getWeather(
        @Header("X-Api-Key") apiKey: String,
        @Query("city") cityName: String,
        @Query("country") countryCode: String
    ): Weather

    @GET("weather/")
    suspend fun getWeather(
        @Header("X-Api-Key") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): Weather


}