package com.trianglz.weatherapp.data.models.weatherdetails

import com.google.gson.annotations.SerializedName

data class WeatherDetailsDataModel(
    @field:SerializedName("lat")
    val latitude: Double? = null,
    @field:SerializedName("lon")
    val longitude: Double? = null,
    @field:SerializedName("timezone")
    val timezone: String? = null,
    @field:SerializedName("timezone_offset")
    val timezoneOffset: Long? = null,
    @field:SerializedName("current")
    val current: Current? = null,
    @field:SerializedName("hourly")
    val hourly: List<Current>? = null,
    @field:SerializedName("daily")
    val daily: List<Daily>? = null
)

data class Current(
    @field:SerializedName("dt")
    val epochTime: Long? = null,
    @field:SerializedName("sunrise")
    val sunrise: Long? = null,
    @field:SerializedName("sunset")
    val sunset: Long? = null,
    @field:SerializedName("temp")
    val temp: Double? = null,
    @field:SerializedName("feels_like")
    val feelsLike: Double? = null,
    @field:SerializedName("pressure")
    val pressure: Long? = null,
    @field:SerializedName("humidity")
    val humidity: Long? = null,
    @field:SerializedName("dew_point")
    val dewPoint: Double? = null,
    @field:SerializedName("uvi")
    val uvIndex: Double? = null,
    @field:SerializedName("clouds")
    val clouds: Long? = null,
    @field:SerializedName("visibility")
    val visibility: Long? = null,
    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,
    @field:SerializedName("wind_deg")
    val windDeg: Long? = null,
    @field:SerializedName("wind_gust")
    val windGust: Double? = null,
    @field:SerializedName("weather")
    val weather: List<Weather>? = null,
    @field:SerializedName("rain")
    val rain: Rain? = null,
    @field:SerializedName("pop")
    val pop: Double? = null
)

data class Rain(
    @field:SerializedName("1h")
    val the1H: Double? = null
)

data class Weather(
    @field:SerializedName("id")
    val id: Long? = null,
    @field:SerializedName("main")
    val main: String? = null,
    @field:SerializedName("description")
    val description: String? = null,
    @field:SerializedName("icon")
    val icon: String? = null
)

data class Daily(
    @field:SerializedName("dt")
    val epochTime: Long? = null,
    @field:SerializedName("sunrise")
    val sunrise: Long? = null,
    @field:SerializedName("sunset")
    val sunset: Long? = null,
    @field:SerializedName("moonrise")
    val moonrise: Long? = null,
    @field:SerializedName("moonset")
    val moonset: Long? = null,
    @field:SerializedName("moon_phase")
    val moonPhase: Double? = null,
    @field:SerializedName("temp")
    val temp: Temp? = null,
    @field:SerializedName("feelsLike")
    val feelsLike: FeelsLike? = null,
    @field:SerializedName("pressure")
    val pressure: Long? = null,
    @field:SerializedName("humidity")
    val humidity: Long? = null,
    @field:SerializedName("dew_point")
    val dewPoint: Double? = null,
    @field:SerializedName("wind_speed")
    val windSpeed: Double? = null,
    @field:SerializedName("wind_deg")
    val windDeg: Long? = null,
    @field:SerializedName("wind_gust")
    val windGust: Double? = null,
    @field:SerializedName("weather")
    val weather: List<Weather>? = null,
    @field:SerializedName("clouds")
    val clouds: Long? = null,
    @field:SerializedName("pop")
    val pop: Double? = null,
    @field:SerializedName("rain")
    val rain: Double? = null,
    @field:SerializedName("uvi")
    val uvIndex: Double? = null
)

data class FeelsLike(
    @field:SerializedName("day")
    val day: Double? = null,
    @field:SerializedName("night")
    val night: Double? = null,
    @field:SerializedName("eve")
    val evening: Double? = null,
    @field:SerializedName("morn")
    val morning: Double? = null
)

data class Temp(
    @field:SerializedName("day")
    val day: Double? = null,
    @field:SerializedName("min")
    val min: Double? = null,
    @field:SerializedName("max")
    val max: Double? = null,
    @field:SerializedName("night")
    val night: Double? = null,
    @field:SerializedName("eve")
    val evening: Double? = null,
    @field:SerializedName("morn")
    val morning: Double? = null
)