package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.BuildConfig
import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.data.apiservice.restcountries.RestCountriesAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Named("ApiKey")
    @Singleton
    fun provideApiKey(): String {
        return BuildConfig.APIKEY
    }

    @Provides
    @Singleton
    fun provideApiNinja(): ApiNinja {
        val baseUrl = "https://api.api-ninjas.com/v1/"

        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ApiNinja::class.java)
    }

    @Provides
    @Singleton
    fun provideRestCountries(): RestCountriesAPI {
        val baseUrl = "https://restcountries.com/v3.1/"

        val retrofit: Retrofit =
            Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(RestCountriesAPI::class.java)
    }

}