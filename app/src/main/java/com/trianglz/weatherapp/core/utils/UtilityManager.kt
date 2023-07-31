package com.trianglz.weatherapp.core.utils

import com.trianglz.weatherapp.core.utils.connection.ConnectionUtility
import com.trianglz.weatherapp.core.utils.exceptionhandler.ExceptionHandler

class UtilityManager(
    private val connectionUtility: ConnectionUtility,
    private val exceptionHandler: ExceptionHandler
) : IUtilityManager {
    override fun isInternetAvailable(): Boolean =
        connectionUtility.isInternetAvailable()

    override fun handleException(throwable: Throwable): String =
        exceptionHandler.handleException(throwable)

}