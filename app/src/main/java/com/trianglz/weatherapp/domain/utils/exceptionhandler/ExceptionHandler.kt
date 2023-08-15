package com.trianglz.weatherapp.domain.utils.exceptionhandler

interface ExceptionHandler {
    fun handleException(throwable: Throwable): String
}