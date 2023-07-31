package com.trianglz.weatherapp.core.utils.exceptionhandler

import retrofit2.HttpException
import java.net.SocketTimeoutException

class ExceptionHandler : IExceptionHandler {
    override fun handleException(throwable: Throwable): String = when (throwable) {
        is HttpException -> handleHttpException(throwable)
        is SocketTimeoutException -> "Request timed out. Please check your internet connection and try again."
        else -> "An error occurred. Please try again later."
    }

    private fun handleHttpException(exception: HttpException): String = when (exception.code()) {
        400 -> "Bad request. Please check your input and try again."
        401 -> "Unauthorized. Please check your Api Key"
        403 -> "Forbidden. You don't have permission to access this resource."
        404 -> "Found no results!"
        500 -> "Internal server error. Please try again later."
        else -> "An error occurred. Please try again later."
    }
}