package com.trianglz.weatherapp.di.modules.app

import android.app.Application
import android.content.res.Resources
import com.trianglz.weatherapp.BuildConfig
import com.trianglz.weatherapp.presentation.exceptionresolver.ExceptionResolver
import com.trianglz.weatherapp.presentation.exceptionresolver.ExceptionResolverImpl
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
     * [provideApiNinjaApiKey] gets the "ApiKey" hidden in "local.properties" file
     * if you are facing problems with the api please get your own api key from the official sites
     * [ApiNinja](https://api-ninjas.com/api/city),
     * [OpenWeatherMap](https://openweathermap.org/api)
     * and put it in "local.properties' as
     *
     *     APININJA_APIKEY=api_key_here
     *     OPENWEATHERMAP_APIKEY=api_key_here
     *
     * */

    companion object {
        @Provides
        @Named("ApiNinjaApiKey")
        @Singleton
        fun provideApiNinjaApiKey(): String {
            return BuildConfig.APININJA_APIKEY
        }
        @Provides
        @Named("OpenWeatherMapApiKey")
        @Singleton
        fun provideOpenWeatherMapApiKey(): String {
            return BuildConfig.OPENWEATHERMAP_APIKEY
        }

        @Provides
        @Singleton
        fun provideResourceManager(
            application: Application
        ): Resources =
            application.resources
    }


    @Binds
    @Singleton
    abstract fun bindsExceptionResolver(exceptionResolver: ExceptionResolverImpl): ExceptionResolver
}