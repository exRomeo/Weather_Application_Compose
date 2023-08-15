package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.data.repository.CitiesRepositoryImpl
import com.trianglz.weatherapp.domain.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CityModule {

    @Binds
    @Singleton
    abstract fun bindsCityRepository(
        citiesRepositoryImpl: CitiesRepositoryImpl
    ): CitiesRepository
}