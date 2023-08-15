package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.data.remotesource.RemoteSourceImpl
import com.trianglz.weatherapp.data.repository.RepositoryImpl
import com.trianglz.weatherapp.domain.repository.Repository
import com.trianglz.weatherapp.domain.utils.UtilityManager
import com.trianglz.weatherapp.domain.utils.UtilityManagerImpl
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandler
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherAppModule {

    @Binds
    @Singleton
    abstract fun bindsRepository(repository: RepositoryImpl): Repository

    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(remoteSourceImpl: RemoteSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsExceptionHandler(exceptionHandler: ExceptionHandlerImpl): ExceptionHandler

    @Binds
    @Singleton
    abstract fun bindUtilityManager(utilityManager: UtilityManagerImpl): UtilityManager

}