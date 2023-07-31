package com.trianglz.weatherapp.core.apiservice.apininja

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNinjaClient {
    private val baseUrl = "https://api.api-ninjas.com/v1/"

    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val ninjaApi: NinjaApi = retrofit.create(NinjaApi::class.java)
}