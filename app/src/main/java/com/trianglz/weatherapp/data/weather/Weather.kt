package com.trianglz.weatherapp.data.weather

import com.google.gson.annotations.SerializedName

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
    val sunset: Long
) {

}