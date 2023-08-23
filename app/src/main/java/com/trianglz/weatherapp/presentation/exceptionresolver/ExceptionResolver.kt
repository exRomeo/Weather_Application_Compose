package com.trianglz.weatherapp.presentation.exceptionresolver

interface ExceptionResolver {

    /**
     * [resolve] takes in a throwable and according to its type it returns
     * a resource id referring to a localized message describing the exception cause
     * */

    fun resolve(throwable: Throwable): String
}