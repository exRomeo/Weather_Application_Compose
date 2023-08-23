package com.trianglz.weatherapp.data.apiservice.city

import com.trianglz.weatherapp.data.models.city.CityDataModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityAPI {

    @GET("city/")
    suspend fun getCities(
        @Header("X-Api-Key") apiKey: String,
        @Query("country") countryCode: String,
        @Query("limit") limit: Int
    ): List<CityDataModel>

}