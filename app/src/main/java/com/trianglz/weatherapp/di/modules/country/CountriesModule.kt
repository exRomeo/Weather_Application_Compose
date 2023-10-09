package com.trianglz.weatherapp.di.modules.country

import com.trianglz.weatherapp.data.remotesource.country.CountriesRemoteSource
import com.trianglz.weatherapp.data.remotesource.country.CountriesRemoteSourceImpl
import com.trianglz.weatherapp.data.repository.country.CountriesRepositoryImpl
import com.trianglz.weatherapp.data.retrofit.apiservice.countries.CountryAPI
import com.trianglz.weatherapp.domain.repository.country.CountriesRepository
import com.trianglz.weatherapp.domain.usecases.countrysearch.FetchCountriesUseCase
import com.trianglz.weatherapp.domain.usecases.countrysearch.FetchCountriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CountriesModule {

    companion object {
        @Provides
        @Singleton
        fun provideCountryApi(okHttpClient: OkHttpClient): CountryAPI {
            val baseUrl = "https://restcountries.com/v3.1/"

            val retrofit: Retrofit =
                Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(CountryAPI::class.java)
        }
    }


    @Binds
    @Singleton
    abstract fun bindsCountriesRemoteSource(
        countriesRemoteSourceImpl: CountriesRemoteSourceImpl
    ): CountriesRemoteSource

    @Binds
    @Singleton
    abstract fun bindsCountriesRepository(
        countriesRepositoryImpl: CountriesRepositoryImpl
    ): CountriesRepository

    @Binds
    @Singleton
    abstract fun bindFetchCountriesUseCase(
        fetchCountriesUseCaseImpl: FetchCountriesUseCaseImpl
    ): FetchCountriesUseCase


}