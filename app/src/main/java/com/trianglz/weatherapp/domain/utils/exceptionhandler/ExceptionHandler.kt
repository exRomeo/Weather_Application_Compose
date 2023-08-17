package com.trianglz.weatherapp.domain.utils.exceptionhandler

interface ExceptionHandler {

    /**
     * [handleException] was created to make easier error messaged for the user to understand
     * @param throwable represents the thrown exception
     **/

    fun handleException(throwable: Throwable): String
}