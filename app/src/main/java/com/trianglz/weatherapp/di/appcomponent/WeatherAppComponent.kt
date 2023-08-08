package com.trianglz.weatherapp.di.appcomponent

import com.trianglz.weatherapp.WeatherApplication
import com.trianglz.weatherapp.di.modules.ActivityBuildersModule
import com.trianglz.weatherapp.di.modules.ApiModule
import com.trianglz.weatherapp.di.modules.ViewModelFactoryModule
import com.trianglz.weatherapp.di.modules.WeatherAppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        WeatherAppModule::class,
        ApiModule::class]
)
interface WeatherAppComponent : AndroidInjector<WeatherApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: WeatherApplication): Builder

        fun build(): WeatherAppComponent

    }

}