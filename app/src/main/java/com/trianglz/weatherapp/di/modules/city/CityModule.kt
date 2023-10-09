package com.trianglz.weatherapp.di.modules.city

import com.trianglz.weatherapp.data.remotesource.city.CitiesRemoteSource
import com.trianglz.weatherapp.data.remotesource.city.CitiesRemoteSourceImpl
import com.trianglz.weatherapp.data.repository.city.CitiesRepositoryImpl
import com.trianglz.weatherapp.data.retrofit.apiservice.city.CityAPI
import com.trianglz.weatherapp.domain.repository.city.CitiesRepository
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCaseImpl
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
abstract class CityModule {


    companion object {
        @Provides
        @Singleton
        fun provideCityAPI(okHttpClient: OkHttpClient): CityAPI {
            val baseUrl = "https://api.api-ninjas.com/v1/"

            val retrofit: Retrofit =
                Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(CityAPI::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindsCityRemoteSource(
        citiesRemoteSourceImpl: CitiesRemoteSourceImpl
    ): CitiesRemoteSource

    @Binds
    @Singleton
    abstract fun bindsCityRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository

    @Binds
    @Singleton
    abstract fun bindsFetchCityUseCase(
        fetchCitiesUseCaseImpl: FetchCitiesUseCaseImpl
    ): FetchCitiesUseCase
}