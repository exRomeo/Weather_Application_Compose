package com.trianglz.weatherapp

import com.trianglz.weatherapp.di.appcomponent.DaggerWeatherAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class WeatherApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerWeatherAppComponent.builder().application(this).build()
    }

}