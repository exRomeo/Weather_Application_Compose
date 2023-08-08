package com.trianglz.weatherapp.di.modules

import com.trianglz.weatherapp.di.scope.MainActivityScope
import com.trianglz.weatherapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    @MainActivityScope
    abstract fun contributeMainActivity(): MainActivity
}