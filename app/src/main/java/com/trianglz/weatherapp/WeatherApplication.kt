package com.trianglz.weatherapp

import android.app.Application
import android.content.Context
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.data.remotesource.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.remotesource.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.repository.Repository
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import com.trianglz.weatherapp.domain.utils.UtilityManager
import com.trianglz.weatherapp.domain.utils.connection.ConnectionUtility
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApplication : Application() {


    val apiNinja: ApiNinja by lazy {
        val baseUrl = "https://api.api-ninjas.com/v1/"

        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(ApiNinja::class.java)
    }


    val restCountriesAPI:RestCountriesAPI by lazy {
        val baseUrl = "https://restcountries.com/v3.1/"

        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(RestCountriesAPI::class.java)
    }


    val utilityManager: IUtilityManager by lazy {
        UtilityManager(
            connectionUtility = ConnectionUtility(this.applicationContext),
            exceptionHandler = ExceptionHandler()
        )
    }


    val repository: IRepository by lazy {
        Repository(
            dataSource = RemoteDataSource(
                apiNinja = apiNinja,
                apiKey = BuildConfig.APIKEY,
                restCountriesAPI = restCountriesAPI
            ),
            utilityManager = utilityManager
        )
    }
}

fun Context.getRestCountriesApi() : RestCountriesAPI = (this as WeatherApplication).restCountriesAPI

fun Context.getApiNinja() : ApiNinja = (this as WeatherApplication).apiNinja

fun Context.getUtilityManager(): IUtilityManager = (this as WeatherApplication).utilityManager

fun Context.getRepository(): IRepository = (this as WeatherApplication).repository