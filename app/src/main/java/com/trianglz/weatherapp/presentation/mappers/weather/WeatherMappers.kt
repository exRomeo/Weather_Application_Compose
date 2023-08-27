package com.trianglz.weatherapp.presentation.mappers.weather

import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel
import kotlin.random.Random


fun WeatherDomainModel.toUiModel(): WeatherUiModel =
    WeatherUiModel(
        currentTemperature = currentTemperature,
        highTemperature = highTemperature,
        lowTemperature = lowTemperature,
        cityName = cityName,
        countryCode = countryCode,
        lat = lat,
        lon = lon,
        description = getWeatherDescription(currentTemperature),
        icon = getWeatherIcon(),
    )

fun WeatherUiModel.toDomainModel(): WeatherDomainModel =
    WeatherDomainModel(
        currentTemperature = currentTemperature,
        highTemperature = highTemperature,
        lowTemperature = lowTemperature,
        cityName = cityName,
        countryCode = countryCode,
        lat = lat,
        lon = lon,
    )


/**
 * due to lack of details about the weather data coming from the api
 * created [getWeatherDescription] to return description based on the current temperature
 * and [getWeatherIcon] to return a random icon to display on the weather card
 * */

private fun getWeatherDescription(currentTemperature: Int): String = when {
    currentTemperature >= 30.0 -> "Hot."
    currentTemperature >= 20.0 -> "Warm."
    currentTemperature >= 10.0 -> "Cool."
    currentTemperature >= 0.0 -> "Freezing!"
    else -> "Stay indoors."
}

private fun getWeatherIcon(): Int {
    return when (Random.nextInt(1, 6)) {
        1 -> R.drawable.moon_cloud_fast_wind
        2 -> R.drawable.sun_cloud_angled_rain
        3 -> R.drawable.moon_cloud_mid_rain
        4 -> R.drawable.sun_cloud_mid_rain
        else -> R.drawable.tornado
    }
}