package com.trianglz.weatherapp.core.apiservice.restcountries

import com.trianglz.weatherapp.core.apiservice.interceptor.RetryInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestCountriesClient {
    private val baseUrl = "https://restcountries.com/v3.1/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(RetryInterceptor(maxRetryCount = 5, retryDelayMillis = 300)).build()

    private val retrofit: Retrofit =
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val restCountriesAPI: RestCountriesAPI = retrofit.create(RestCountriesAPI::class.java)
}