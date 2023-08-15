package com.trianglz.weatherapp.di.modules.app

import com.trianglz.weatherapp.BuildConfig
import com.trianglz.weatherapp.domain.utils.UtilityManager
import com.trianglz.weatherapp.domain.utils.UtilityManagerImpl
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandler
import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandlerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    companion object{
        @Provides
        @Named("ApiKey")
        @Singleton
        fun provideApiKey(): String {
            return BuildConfig.APIKEY
        }
    }

    @Binds
    @Singleton
    abstract fun bindsExceptionHandler(exceptionHandler: ExceptionHandlerImpl): ExceptionHandler

    @Binds
    @Singleton
    abstract fun bindUtilityManager(utilityManager: UtilityManagerImpl): UtilityManager

}