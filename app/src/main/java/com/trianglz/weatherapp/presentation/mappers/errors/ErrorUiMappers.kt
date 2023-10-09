package com.trianglz.weatherapp.presentation.mappers.errors

import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel

fun WeatherAppErrorDomainModel.toUi(): Int =
    when (this) {
        is WeatherAppErrorDomainModel.NoInternetConnection -> R.string.connection_error
        is WeatherAppErrorDomainModel.RequestTimedOut -> R.string.request_timed_out
        is WeatherAppErrorDomainModel.BadRequest -> R.string.bad_request
        is WeatherAppErrorDomainModel.Unauthorized -> R.string.unauthorized
        is WeatherAppErrorDomainModel.Forbidden -> R.string.forbidden
        is WeatherAppErrorDomainModel.NotFound -> R.string.not_found
        is WeatherAppErrorDomainModel.TooManyRequests -> R.string.too_many_requests
        is WeatherAppErrorDomainModel.InternalServerError -> R.string.internal_server_error
        is WeatherAppErrorDomainModel.SomethingWentWrong -> R.string.unknown_error
    }