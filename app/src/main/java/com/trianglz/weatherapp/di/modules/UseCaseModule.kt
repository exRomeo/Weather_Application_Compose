package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.domain.usecases.countrysearch.CountrySearchUseCase
import com.trianglz.weatherapp.domain.usecases.countrysearch.ICountrySearchUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.IWeatherDataUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCase
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
    abstract fun bindCountrySearchUseCase(countrySearchUseCase: CountrySearchUseCase): ICountrySearchUseCase

    @Binds
    @Singleton
    abstract fun bindWeatherDataUseCAse(weatherDataUseCase: WeatherDataUseCase): IWeatherDataUseCase
}