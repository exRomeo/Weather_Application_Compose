package com.trianglz.weatherapp.domain.models.weatherdetails

class WeatherDetailsDomainModel(
    val latitude: Double,
    val longitude: Double,
    val cityName: String,
    val countryCode: String,
    val currentEpochTime: Long,
    val sunrise: Long,
    val sunset: Long,
    val currentTemperature: Double,
    val feelsLike: Double,
    val high: Double,
    val low: Double,
    val pressure: Long,
    val humidity: Long,
    val uvIndex: Double,
    val clouds: Long,
    val visibility: Long,
    val windSpeed: Double,
    val windDeg: Long,
    val pop: Double,
    val description: String,
    val icon: String,
    val hourlyList: List<HourlyDomainModel>,
    val dailyList: List<DailyDomainModel>
)

data class HourlyDomainModel(
    val epochTime: Long,
    val temperature: Double,
    val icon: String,
    val pop: Double
)

data class DailyDomainModel(
    val epochTime: Long,
    val icon: String,
    val pop: Double,
    val high: Double,
    val low: Double
)