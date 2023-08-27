package com.trianglz.weatherapp.di.modules.weatherdetails

import com.trianglz.weatherapp.data.apiservice.weatherdetails.WeatherDetailsAPI
import com.trianglz.weatherapp.data.remotesource.weatherdetails.WeatherDetailsRemoteSource
import com.trianglz.weatherapp.data.remotesource.weatherdetails.WeatherDetailsRemoteSourceImpl
import com.trianglz.weatherapp.data.repository.weatherdetails.WeatherDetailsRepositoryImpl
import com.trianglz.weatherapp.domain.repository.weatherdetails.WeatherDetailsRepository
import com.trianglz.weatherapp.domain.usecases.fetchweatherdetails.FetchWeatherDetailsUseCase
import com.trianglz.weatherapp.domain.usecases.fetchweatherdetails.FetchWeatherDetailsUseCaseImpl
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
abstract class WeatherDetailsModule {

    companion object {
        @Provides
        @Singleton
        fun providesOpenWeatherMapApi(): WeatherDetailsAPI {
            val baseUrl = "https://api.openweathermap.org/"

            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherDetailsAPI::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindsWeatherDetailsRemoteSource(
        weatherDetailsRemoteSource: WeatherDetailsRemoteSourceImpl
    ): WeatherDetailsRemoteSource

    @Binds
    @Singleton
    abstract fun bindsWeatherDetailsRepository(
        weatherDetailsRepository: WeatherDetailsRepositoryImpl
    ): WeatherDetailsRepository


    @Binds
    @Singleton
    abstract fun bindsWeatherDetailsUseCase(
        fetchWeatherDetailsUseCaseImpl: FetchWeatherDetailsUseCaseImpl
    ): FetchWeatherDetailsUseCase

}