package com.trianglz.weatherapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trianglz.weatherapp.presentation.ui.home.HomeViewModel
import com.trianglz.weatherapp.presentation.ui.viewmodels.DaggerViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    @IntoMap
    @ClassKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

}