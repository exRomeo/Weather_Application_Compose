package com.trianglz.weatherapp.di.modules

import android.app.Application
import com.trianglz.weatherapp.WeatherApplication
import com.trianglz.weatherapp.data.remotesource.RemoteDataSource
import com.trianglz.weatherapp.data.repository.Repository
import com.trianglz.weatherapp.domain.remotesource.IRemoteDataSource
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import com.trianglz.weatherapp.domain.utils.UtilityManager
import com.trianglz.weatherapp.domain.utils.connection.ConnectionUtility
import com.trianglz.weatherapp.domain.utils.connection.IConnectionUtility
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandler
import com.trianglz.weatherapp.domain.utils.exceptionhandler.IExceptionHandler
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class WeatherAppModule {

    @Binds
    abstract fun bindsWeatherApplication(weatherApplication: WeatherApplication): Application

    @Binds
    @Singleton
    abstract fun bindsRepository(repository: Repository): IRepository

    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(remoteDataSource: RemoteDataSource): IRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideUtilityManager(connectionUtility: ConnectionUtility):IConnectionUtility

    @Binds
    @Singleton
    abstract fun bindsExceptionHandler(exceptionHandler: ExceptionHandler): IExceptionHandler

    @Binds
    @Singleton
    abstract fun bindUtilityManager(utilityManager: UtilityManager): IUtilityManager
}