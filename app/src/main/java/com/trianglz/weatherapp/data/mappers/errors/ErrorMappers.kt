package com.trianglz.weatherapp.data.mappers.errors

import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel


fun WeatherAppErrorDataModel.toDomainModel(): WeatherAppErrorDomainModel =
    when(this){
        is WeatherAppErrorDataModel.NoInternetConnection -> WeatherAppErrorDomainModel.NoInternetConnection(message)
        is WeatherAppErrorDataModel.BadRequest -> WeatherAppErrorDomainModel.BadRequest(message)
        is WeatherAppErrorDataModel.Forbidden ->  WeatherAppErrorDomainModel.Forbidden(message)
        is WeatherAppErrorDataModel.InternalServerError -> WeatherAppErrorDomainModel.InternalServerError(message)
        is WeatherAppErrorDataModel.NotFound -> WeatherAppErrorDomainModel.NotFound(message)
        is WeatherAppErrorDataModel.RequestTimedOut -> WeatherAppErrorDomainModel.RequestTimedOut(message)
        is WeatherAppErrorDataModel.TooManyRequests -> WeatherAppErrorDomainModel.TooManyRequests(message)
        is WeatherAppErrorDataModel.Unauthorized -> WeatherAppErrorDomainModel.Unauthorized(message)
        is WeatherAppErrorDataModel.SomethingWentWrong ->  WeatherAppErrorDomainModel.SomethingWentWrong(message)
    }