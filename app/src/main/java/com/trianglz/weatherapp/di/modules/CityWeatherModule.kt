package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.data.repository.CityWeatherRepositoryImpl
import com.trianglz.weatherapp.domain.repository.CityWeatherRepository
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCase
import com.trianglz.weatherapp.domain.usecases.getweatherdata.WeatherDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CityWeatherModule {

    @Binds
    @Singleton
    abstract fun bindsWeatherCityRepository(
        cityWeatherRepositoryImpl: CityWeatherRepositoryImpl
    ): CityWeatherRepository

    @Binds
    @Singleton
    abstract fun bindsWeatherDataUseCase(
        weatherDataUseCase: WeatherDataUseCaseImpl
    ): WeatherDataUseCase
}