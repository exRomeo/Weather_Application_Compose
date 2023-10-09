package com.trianglz.weatherapp.data.remotesource.weather

import arrow.core.Either
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel

interface WeatherRemoteSource {

    /**
     * [getWeather] fetches the weather data from the api by specifying the city's [longitude] and [latitude]
     * */

    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): Either<WeatherAppErrorDataModel, WeatherDataModel>
}