package com.trianglz.weatherapp.presentation.models.weathercard

import androidx.annotation.DrawableRes
import com.trianglz.weatherapp.R

data class WeatherUiModel(
    val currentTemperature: Int = 0,
    val highTemperature: Int = 0,
    val lowTemperature: Int = 0,
    val cityName: String = "Somewhere",
    val countryCode: String = "planet Earth",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val description: String = "A description fit for some where on planet earth",
    @DrawableRes val icon: Int = R.drawable.sun_cloud_angled_rain,
)

