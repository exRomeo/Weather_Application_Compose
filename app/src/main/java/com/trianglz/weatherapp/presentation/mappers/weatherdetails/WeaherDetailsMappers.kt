package com.trianglz.weatherapp.presentation.mappers.weatherdetails

import android.util.Log
import com.trianglz.weatherapp.R
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.CurrentWeatherUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.DailyUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.ExtraDetailUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.HourlyUiModel
import com.trianglz.weatherapp.presentation.models.weatherdetails.WeatherDetailsUiModel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

fun WeatherDetailsDomainModel.toUiModel(): WeatherDetailsUiModel =
    WeatherDetailsUiModel(
        currentWeatherDetails = CurrentWeatherUiModel(
            cityName = cityName,
            countryCode = countryCode,
            latitude = latitude,
            longitude = longitude,
            currentTemperature = "${(currentTemperature * 10).roundToInt() / 10.0}",
            feelsLike = "${(feelsLike * 10).roundToInt() / 10.0}",
            description = description,
            high = "${(high * 10).roundToInt() / 10.0}",
            low = "${(low * 10).roundToInt() / 10.0}",
            icon = getResourceIdForIcon(icon)
        ),
        extraDetailsList = listOf(
            ExtraDetailUiModel(
                name = "Sunrise",
                value = epochToText(sunrise, "h:mm a"),
                icon = R.drawable.sunrise
            ),
            ExtraDetailUiModel(
                name = "Sunset",
                value = epochToText(sunset, "h:mm a"),
                icon = R.drawable.sunset
            ),
            ExtraDetailUiModel(
                name = "Humidity",
                value = "$humidity%",
                icon = R.drawable.humidity
            ),
            ExtraDetailUiModel(
                name = "Pressure",
                value = "$pressure pHa",
                icon = R.drawable.air_pressure
            ),
            ExtraDetailUiModel(
                name = "Cloudiness",
                value = "$clouds%",
                icon = R.drawable.clouds
            ),
            ExtraDetailUiModel(
                name = "UV Index",
                value = "$uvIndex",
                icon = R.drawable.uv_index
            ),
            ExtraDetailUiModel(
                name = "Visibility",
                value = "${visibility / 1000} km",
                icon = R.drawable.visibility
            ),
            ExtraDetailUiModel(
                name = "Wind Speed",
                value = "$windSpeed m/s",
                icon = R.drawable.wind_speed
            ),
            ExtraDetailUiModel(
                name = "Wind Degree",
                value = "${degreesToWindDirection(windDeg)}°",
                icon = R.drawable.wind_degree
            ),
        ),
        hourlyList = hourlyList.map {
            HourlyUiModel(
                time = epochToText(it.epochTime, "ha"),
                icon = getResourceIdForIcon(it.icon),
                temperature = "${(it.temperature* 10).roundToInt() / 10.0}°",
                pop = "${getPopText(it.pop)}%"
            )
        },
        dailyList = dailyList.map {
            DailyUiModel(
                time = epochToText(it.epochTime, "EEE dd/MM"),
                icon = getResourceIdForIcon(it.icon),
                pop = "${getPopText(it.pop)}%",
                high = "${(it.high * 10).roundToInt() / 10.0}°",
                low = "${(it.low * 10).roundToInt() / 10.0}°",
            )
        }
    )


private fun getResourceIdForIcon(icon: String): Int =
    when (icon) {
        "01d" -> R.raw.clear_sky_01d
        "01n" -> R.raw.clear_sky_01n
        "02d" -> R.raw.few_clouds_02d
        "02n" -> R.raw.few_clouds_02n
        "03d" -> R.raw.scattered_clouds_03d
        "03n" -> R.raw.scattered_clouds_03n
        "04d" -> R.raw.broken_clouds_04d
        "04n" -> R.raw.broken_clouds_04n
        "09d" -> R.raw.shower_rain_09d
        "09n" -> R.raw.shower_rain_09n
        "10d" -> R.raw.rain_10d
        "10n" -> R.raw.rain_10n
        "11d" -> R.raw.thunderstorm_11d
        "11n" -> R.raw.thunderstorm_11n
        "13d" -> R.raw.snow_13d
        "13n" -> R.raw.snow_13n
        "50d" -> R.raw.mist_50d
        "50n" -> R.raw.mist_50n
        else -> {
            R.raw.unknown_weather
            Log.i("TAG", "getResourceIdForIcon: $icon")
        }
    }

private fun epochToText(epoch: Long, pattern: String): String {
    val dtf =
        DateTimeFormatter.ofPattern(pattern)
    return Instant.ofEpochSecond(epoch).atZone(ZoneId.systemDefault()).format(dtf)
}


private fun degreesToWindDirection(degrees: Long): String {
    val directions = listOf(
        "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
        "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N"
    )
    val index = ((degrees + 11.25) % 360 / 22.5).toInt()
    return directions[index]
}

private fun getPopText(pop: Double): String {
    return (pop * 100).roundToInt().toString()
}