package com.trianglz.weatherapp.domain.utils

import com.trianglz.weatherapp.domain.utils.exceptionhandler.IExceptionHandler
import javax.inject.Inject

class UtilityManager @Inject constructor(
    private val exceptionHandler: IExceptionHandler
) : IUtilityManager {

    override fun handleException(exception: Exception): String =
        exceptionHandler.handleException(exception)

}