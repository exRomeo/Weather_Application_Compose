package com.trianglz.weatherapp.di.modules.city

import com.trianglz.weatherapp.data.remotesource.city.CitiesRemoteSource
import com.trianglz.weatherapp.data.remotesource.city.CitiesRemoteSourceImpl
import com.trianglz.weatherapp.data.repository.city.CitiesRepositoryImpl
import com.trianglz.weatherapp.domain.repository.city.CitiesRepository
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCase
import com.trianglz.weatherapp.domain.usecases.fetchcities.FetchCitiesUseCaseImpl
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