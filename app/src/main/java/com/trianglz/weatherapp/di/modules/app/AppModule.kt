package com.trianglz.weatherapp.di.modules.app

import com.trianglz.weatherapp.BuildConfig
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
    /**
     * [provideApiKey] gets the "ApiKey" hidden in "local.properties" file
     * if you are facing problems with the api please get your own api key from the [official site](https://api-ninjas.com/api/city)
     * and put it in "local.properties' as
     *
     *     APIKEY=api_key_here
     *
     * */

    companion object {
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

}