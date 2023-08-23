package com.trianglz.weatherapp.presentation.mappers.weather

import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.presentation.extensions.getWeatherDescription
import com.trianglz.weatherapp.presentation.extensions.getWeatherIcon
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel


fun WeatherDomainModel.toUiModel(): WeatherUiModel =
    WeatherUiModel(
        currentTemperature = currentTemperature,
        highTemperature = highTemperature,
        lowTemperature = lowTemperature,
        location = "$cityName, $countryCode",
        description = getWeatherDescription(),
        icon = getWeatherIcon(),
    )