package com.trianglz.weatherapp.domain.utils.exceptionhandler

interface IExceptionHandler {
    fun handleException(exception: Exception): String
}