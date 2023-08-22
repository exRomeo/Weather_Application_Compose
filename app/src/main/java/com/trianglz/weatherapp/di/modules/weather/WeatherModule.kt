package com.trianglz.weatherapp.di.modules.weather

import com.trianglz.weatherapp.data.apiservice.weather.WeatherAPI
import com.trianglz.weatherapp.data.remotesource.weather.WeatherRemoteSource
import com.trianglz.weatherapp.data.remotesource.weather.WeatherRemoteSourceImpl
import com.trianglz.weatherapp.data.repository.weather.WeatherRepositoryImpl
import com.trianglz.weatherapp.domain.repository.weather.WeatherRepository
import com.trianglz.weatherapp.domain.usecases.fetchweather.FetchWeatherUseCase
import com.trianglz.weatherapp.domain.usecases.fetchweather.FetchWeatherUseCaseImpl
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
abstract class WeatherModule {

    companion object {
        @Provides
        @Singleton
        fun provideWeatherAPI(): WeatherAPI {
            val baseUrl = "https://api.api-ninjas.com/v1/"

            val retrofit: Retrofit =
                Retrofit
                    .Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WeatherAPI::class.java)
        }
    }

    @Binds
    @Singleton
    abstract fun bindsWeatherRemoteSource(
        weatherRemoteSourceImpl: WeatherRemoteSourceImpl
    ): WeatherRemoteSource

    @Binds
    @Singleton
    abstract fun bindsWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindsFetchWeatherUseCase(
        fetchWeatherUseCaseImpl: FetchWeatherUseCaseImpl
    ): FetchWeatherUseCase

}