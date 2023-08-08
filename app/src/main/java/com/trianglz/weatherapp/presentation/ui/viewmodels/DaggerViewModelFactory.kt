package com.trianglz.weatherapp.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DaggerViewModelFactory @Inject constructor(private val viewModels: Map<Class<*>, @JvmSuppressWildcards ViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var current = viewModels[modelClass]
        if (current != null)
            current = viewModels.asIterable().firstOrNull()?.value

        current?.let {
            if (modelClass.isAssignableFrom(it::class.java))
                return it as T
        }
        throw IllegalArgumentException("ViewModel Class Not Found")
    }
}