package com.trianglz.weatherapp.domain.utils.connection

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import javax.inject.Inject

class ConnectionUtility @Inject constructor(application: Application) : IConnectionUtility {

    private val connectivityManager = application.getSystemService(
        ConnectivityManager::class.java
    )

    override fun isInternetAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}