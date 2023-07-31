package com.trianglz.weatherapp.core.apiservice.restcountries

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestCountriesClient {
    private val baseUrl = "https://restcountries.com/v3.1/"

    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val restCountriesAPI: RestCountriesAPI = retrofit.create(RestCountriesAPI::class.java)
}