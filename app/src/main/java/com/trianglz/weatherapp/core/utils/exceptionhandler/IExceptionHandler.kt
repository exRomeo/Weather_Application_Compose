package com.trianglz.weatherapp.core.utils.exceptionhandler

interface IExceptionHandler {
    fun handleException(throwable: Throwable): String
}