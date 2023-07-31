package com.trianglz.weatherapp.data.models.weather

import com.google.gson.annotations.SerializedName
import com.trianglz.weatherapp.R
import kotlin.random.Random

class Weather(
    @field:SerializedName("cloud_pct")
    val cloudPercentage: Int,
    @field:SerializedName("temp")
    val currentTemperature: Int,
    @field:SerializedName("feels_like")
    val feelsLike: Int,
    @field:SerializedName("humidity")
    val humidityPercentage: Int,
    @field:SerializedName("min_temp")
    val lowTemperature: Int,
    @field:SerializedName("max_temp")
    val highTemperature: Int,
    @field:SerializedName("wind_speed")
    val windSpeed: Double,
    @field:SerializedName("wind_degree")
    val windDegree: Int,
    @field:SerializedName("sunrise")
    val sunrise: Long,
    @field:SerializedName("sunset")
    val sunset: Long,
    var countryCode: String? = null,
    var cityName: String? = null
) {

    fun getWeatherDescription(): String = when {
        currentTemperature >= 30.0 -> "Hot."
        currentTemperature >= 20.0 -> "Warm."
        currentTemperature >= 10.0 -> "Cool."
        currentTemperature >= 0.0 -> "Freezing!"
        else -> "Stay indoors."
    }

    fun getWeatherIcon(): Int {
        return when (Random.nextInt(1, 6)) {
            1 -> R.drawable.moon_cloud_fast_wind
            2 -> R.drawable.sun_cloud_angled_rain
            3 -> R.drawable.moon_cloud_mid_rain
            4 -> R.drawable.sun_cloud_mid_rain
            else -> R.drawable.tornado
        }
    }
}