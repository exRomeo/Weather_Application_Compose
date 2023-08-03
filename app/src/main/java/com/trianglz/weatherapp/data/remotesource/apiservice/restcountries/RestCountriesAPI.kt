package com.trianglz.weatherapp.data.remotesource.apiservice.restcountries

import com.trianglz.weatherapp.data.models.country.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface RestCountriesAPI {
    @GET("name/{name}")
    suspend fun getCountries(@Path("name") countryName:String):List<Country>
}