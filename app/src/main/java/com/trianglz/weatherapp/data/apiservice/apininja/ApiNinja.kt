package com.trianglz.weatherapp.data.apiservice.apininja

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiNinja {

    @GET("city/")
    suspend fun getCities(
        @Header("X-Api-Key") apiKey: String,
        @Query("country") countryCode: String,
        @Query("limit") limit: Int
    ): List<CityDataModel>

    @GET("weather/")
    suspend fun getWeather(
        @Header("X-Api-Key") apiKey: String,
        @Query("city") cityName: String,
        @Query("country") countryCode: String
    ): WeatherDataModel

    @GET("weather/")
    suspend fun getWeather(
        @Header("X-Api-Key") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): WeatherDataModel


}