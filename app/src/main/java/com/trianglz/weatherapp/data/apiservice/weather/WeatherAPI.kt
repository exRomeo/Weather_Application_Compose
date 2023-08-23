package com.trianglz.weatherapp.data.apiservice.weather

import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather/")
    suspend fun getWeather(
        @Header("X-Api-Key") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): WeatherDataModel

}