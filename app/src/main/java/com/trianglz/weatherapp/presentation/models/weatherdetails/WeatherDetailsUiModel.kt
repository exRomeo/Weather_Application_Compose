package com.trianglz.weatherapp.presentation.models.weatherdetails

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import com.trianglz.weatherapp.R



data class WeatherDetailsUiModel (
    val currentWeatherDetails: CurrentWeatherUiModel,
    val hourlyList: List<HourlyUiModel>,
    val extraDetailsList: List<ExtraDetailUiModel>,
    val dailyList: List<DailyUiModel>
)

data class CurrentWeatherUiModel(
    val cityName: String = "Alexandria",
    val countryCode:String = "EG",
    val latitude: Double = 9.1545,
    val longitude: Double = 8.995258,
    val currentTemperature: String = "39.393",
    val feelsLike: String = "56.32",
    val description: String = "Scorching Hot",
    val high: String = "70.5",
    val low: String = "38.12",
    @RawRes val icon: Int = R.raw.clear_sky_01d
)

data class HourlyUiModel(
    val time: String = "12 Am",
    @RawRes val icon: Int = R.raw.few_clouds_02n,
    val temperature: String = "19.45°",
    val pop: String = "0.85"

)

data class DailyUiModel(
    val time: String = "Sun 26/08",
    @RawRes val icon: Int = R.raw.mist_50n,
    val pop: String = "30.1",
    val high: String = "30",
    val low: String = "25"
)


data class ExtraDetailUiModel(
    val name: String = "Pressure",
    val value: String = "1000hpa",
    @DrawableRes val icon: Int = R.drawable.air_pressure
)
