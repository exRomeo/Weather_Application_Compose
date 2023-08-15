package com.trianglz.weatherapp.domain.utils

import com.trianglz.weatherapp.domain.utils.exceptionhandler.ExceptionHandler
import javax.inject.Inject

class UtilityManagerImpl @Inject constructor(
    private val exceptionHandler: ExceptionHandler
) : UtilityManager {

    override fun handleException(exception: Exception): String =
        exceptionHandler.handleException(exception)

}