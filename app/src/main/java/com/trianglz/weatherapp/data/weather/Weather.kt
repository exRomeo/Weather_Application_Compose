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
    companion object{
        fun getDummyData(): List<Weather> =
            listOf(
                Weather(
                    cloudPercentage = 15,
                    currentTemperature = 30,
                    feelsLike = 55,
                    humidityPercentage = 93,
                    lowTemperature = 10,
                    highTemperature = 300,
                    windSpeed = 13.2,
                    windDegree = 210,
                    sunrise = 562165115163,
                    sunset = 315656565656
                ),
                Weather(
                    cloudPercentage = 15,
                    currentTemperature = 30,
                    feelsLike = 55,
                    humidityPercentage = 93,
                    lowTemperature = 10,
                    highTemperature = 300,
                    windSpeed = 13.2,
                    windDegree = 210,
                    sunrise = 512165165163,
                    sunset = 315656565656
                ),
                Weather(
                    cloudPercentage = 15,
                    currentTemperature = 30,
                    feelsLike = 55,
                    humidityPercentage = 93,
                    lowTemperature = 10,
                    highTemperature = 300,
                    windSpeed = 13.2,
                    windDegree = 210,
                    sunrise = 563165165163,
                    sunset = 315656565656
                ),
                Weather(
                    cloudPercentage = 15,
                    currentTemperature = 30,
                    feelsLike = 55,
                    humidityPercentage = 93,
                    lowTemperature = 10,
                    highTemperature = 300,
                    windSpeed = 13.2,
                    windDegree = 210,
                    sunrise = 162165165163,
                    sunset = 315656565656
                ),
                Weather(
                    cloudPercentage = 15,
                    currentTemperature = 30,
                    feelsLike = 55,
                    humidityPercentage = 93,
                    lowTemperature = 10,
                    highTemperature = 300,
                    windSpeed = 13.2,
                    windDegree = 210,
                    sunrise = 562165165166,
                    sunset = 315656565656
                ),
            )
    }

}