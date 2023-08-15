package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.domain.usecases.countrysearch.CountrySearchUseCase
import com.trianglz.weatherapp.domain.usecases.countrysearch.CountrySearchUseCaseImpl
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindCountrySearchUseCase(countrySearchUseCase: CountrySearchUseCaseImpl): CountrySearchUseCase

    @Binds
    @Singleton
    abstract fun bindWeatherDataUseCAse(weatherDataUseCase: WeatherDataUseCaseImpl): WeatherDataUseCase
}