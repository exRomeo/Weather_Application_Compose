package com.trianglz.weatherapp.data.models.errors

sealed class WeatherAppErrorDataModel(val message: String) {
    class NoInternetConnection(message: String) : WeatherAppErrorDataModel(message)
    class RequestTimedOut(message: String) : WeatherAppErrorDataModel(message)
    class BadRequest(message: String) : WeatherAppErrorDataModel(message)
    class Unauthorized(message: String) : WeatherAppErrorDataModel(message)
    class Forbidden(message: String) : WeatherAppErrorDataModel(message)
    class NotFound(message: String) : WeatherAppErrorDataModel(message)
    class TooManyRequests(message: String) : WeatherAppErrorDataModel(message)
    class InternalServerError(message: String) : WeatherAppErrorDataModel(message)
    class SomethingWentWrong(message: String) : WeatherAppErrorDataModel(message)
}