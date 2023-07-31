package com.trianglz.weatherapp.core.utils.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class ConnectionUtility(context: Context) : IConnectionUtility {

    private val connectivityManager = context.getSystemService(
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