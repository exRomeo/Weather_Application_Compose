package com.trianglz.weatherapp.data.mappers.errors

import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * [resolve] takes in a throwable and according to its type it returns
 * a typed error making it easier to handle errors
 * */

fun resolve(throwable: Throwable): WeatherAppErrorDataModel = when (throwable) {
    is HttpException -> resolveHttpException(exception = throwable)
    is SocketTimeoutException -> WeatherAppErrorDataModel.RequestTimedOut(
        message = throwable.message ?: "Request timed out!"
    )

    is IOException -> WeatherAppErrorDataModel.NoInternetConnection(
        message = throwable.message ?: "No internet connection!"
    )

    else -> WeatherAppErrorDataModel.SomethingWentWrong(
        message = throwable.message ?: "Something went wrong!"
    )
}


/**
 * [resolveHttpException] as its name suggest was created specifically for [HttpException] codes
 * and resolves http codes to a Typed Error for easier error handling
 * */

private fun resolveHttpException(exception: HttpException): WeatherAppErrorDataModel =
    when (exception.code()) {
        400 -> WeatherAppErrorDataModel.BadRequest(message = exception.message() ?: "Bad request!")
        401 -> WeatherAppErrorDataModel.Unauthorized(
            message = exception.message() ?: "Unauthorized!"
        )

        403 -> WeatherAppErrorDataModel.Forbidden(message = exception.message() ?: "Forbidden!")
        404 -> WeatherAppErrorDataModel.NotFound(message = exception.message() ?: "Not found!")
        429 -> WeatherAppErrorDataModel.TooManyRequests(
            message = exception.message() ?: "Too many requests!"
        )

        500 -> WeatherAppErrorDataModel.InternalServerError(
            message = exception.message() ?: "Internal server error!"
        )

        else -> WeatherAppErrorDataModel.SomethingWentWrong(
            message = exception.message() ?: "Something went wrong!"
        )
    }