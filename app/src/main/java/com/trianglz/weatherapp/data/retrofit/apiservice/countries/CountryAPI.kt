package com.trianglz.weatherapp.data.retrofit.apiservice.countries

import com.trianglz.weatherapp.data.models.country.CountryDataModel
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryAPI {
    @GET("name/{name}")
    suspend fun getCountries(@Path("name") countryName: String): List<CountryDataModel>
}