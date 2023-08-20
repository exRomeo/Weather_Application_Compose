package com.trianglz.weatherapp.presentation.exceptionresolver

import android.content.res.Resources
import com.trianglz.weatherapp.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class ExceptionResolverImpl @Inject constructor(private val resourceManager: Resources) :
    ExceptionResolver {

    /**
     * [resolve] takes in a throwable and according to its type it returns
     * a resource id referring to a localized message describing the exception cause
     * */

    override fun resolve(throwable: Throwable): String {

        val id = when (throwable) {
            is HttpException -> resolveHttpException(exception = throwable)
            is SocketTimeoutException -> R.string.request_timed_out
            is IOException -> R.string.connection_error
            else -> R.string.unknown_error
        }

        return resourceManager.getString(id)
    }

    /**
     * [resolveHttpException] as its name suggest was created specifically for [HttpException] codes
     * and resolves http codes to a message the user can understand
     * */

    private fun resolveHttpException(exception: HttpException): Int = when (exception.code()) {
        400 -> R.string.bad_request
        401 -> R.string.unauthorized
        403 -> R.string.forbidden
        404 -> R.string.not_found
        500 -> R.string.internal_server_error
        else -> R.string.unknown_error
    }
}