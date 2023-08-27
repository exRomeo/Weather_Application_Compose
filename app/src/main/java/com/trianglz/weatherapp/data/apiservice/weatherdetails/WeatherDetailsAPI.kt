package com.trianglz.weatherapp.data.apiservice.weatherdetails

import com.trianglz.weatherapp.data.models.weatherdetails.WeatherDetailsDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDetailsAPI {

    @GET("/data/2.5/onecall")
    suspend fun oneCall(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("lang") lang: String,
        @Query("units") unit:String,
        @Query("exclude") exclude:String,
        @Query("appid") apiKey:String
    ): WeatherDetailsDataModel

}