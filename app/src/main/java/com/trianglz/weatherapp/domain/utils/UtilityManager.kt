package com.trianglz.weatherapp.domain.utils

import com.trianglz.weatherapp.domain.utils.connection.IConnectionUtility
import com.trianglz.weatherapp.domain.utils.exceptionhandler.IExceptionHandler

class UtilityManager(
    private val connectionUtility: IConnectionUtility,
    private val exceptionHandler: IExceptionHandler
) : IUtilityManager {
    override fun isInternetAvailable(): Boolean =
        connectionUtility.isInternetAvailable()

    override fun handleException(exception: Exception): String =
        exceptionHandler.handleException(exception)

}