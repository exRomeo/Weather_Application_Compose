package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCaseImpl
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

    companion object{
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
    }

    @Binds
    @Singleton
    abstract fun bindWeatherDataUseCAse(weatherDataUseCase: WeatherDataUseCaseImpl): WeatherDataUseCase
}