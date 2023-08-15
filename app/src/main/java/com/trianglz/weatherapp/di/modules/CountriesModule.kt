package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.data.apiservice.restcountries.RestCountriesAPI
import com.trianglz.weatherapp.data.repository.CountriesRepositoryImpl
import com.trianglz.weatherapp.domain.repository.CountriesRepository
import com.trianglz.weatherapp.domain.usecases.countrysearch.CountrySearchUseCase
import com.trianglz.weatherapp.domain.usecases.countrysearch.CountrySearchUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CountriesModule {

    companion object {
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


    @Binds
    @Singleton
    abstract fun bindsCountriesRepository(
        countriesRepositoryImpl: CountriesRepositoryImpl
    ): CountriesRepository

    @Binds
    @Singleton
    abstract fun bindCountrySearchUseCase(
        countrySearchUseCase: CountrySearchUseCaseImpl
    ): CountrySearchUseCase


}