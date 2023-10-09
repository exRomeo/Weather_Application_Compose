package com.trianglz.weatherapp.domain.models.errors

sealed class WeatherAppErrorDomainModel(val message: String) {
    class NoInternetConnection(message: String) : WeatherAppErrorDomainModel(message)
    class RequestTimedOut(message: String) : WeatherAppErrorDomainModel(message)
    class BadRequest(message: String) : WeatherAppErrorDomainModel(message)
    class Unauthorized(message: String) : WeatherAppErrorDomainModel(message)
    class Forbidden(message: String) : WeatherAppErrorDomainModel(message)
    class NotFound(message: String) : WeatherAppErrorDomainModel(message)
    class TooManyRequests(message: String) : WeatherAppErrorDomainModel(message)
    class InternalServerError(message: String) : WeatherAppErrorDomainModel(message)
    class SomethingWentWrong(message: String) : WeatherAppErrorDomainModel(message)
}