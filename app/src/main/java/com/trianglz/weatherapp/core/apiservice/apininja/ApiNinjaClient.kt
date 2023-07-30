package com.trianglz.weatherapp.core.apiservice.apininja

import com.trianglz.weatherapp.core.apiservice.interceptor.RetryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiNinjaClient {
    private val baseUrl = "https://api.api-ninjas.com/v1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(RetryInterceptor(maxRetryCount = 5, retryDelayMillis = 300)).build()
    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val ninjaApi: NinjaApi = retrofit.create(NinjaApi::class.java)
}