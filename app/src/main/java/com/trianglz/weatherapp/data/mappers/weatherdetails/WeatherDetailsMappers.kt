package com.trianglz.weatherapp.data.mappers.weatherdetails

import com.trianglz.weatherapp.data.models.weatherdetails.WeatherDetailsDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.DailyDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.HourlyDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel

/**
 * [toDomainModel] as its name suggests maps [WeatherDetailsDataModel] to [WeatherDetailsDomainModel] and adds the necessary fields which aren't provided by the api
 */
fun WeatherDetailsDataModel.toDomainModel(weather: WeatherDomainModel): WeatherDetailsDomainModel =
    WeatherDetailsDomainModel(
        latitude = latitude ?: weather.lat,
        longitude = longitude ?: weather.lon,
        cityName = weather.cityName,
        countryCode = weather.countryCode,
        currentEpochTime = current?.epochTime ?: 0,
        sunrise = current?.sunrise ?: 0,
        sunset = current?.sunset ?: 0,
        currentTemperature = current?.temp ?: 0.0,
        feelsLike = current?.feelsLike ?: 0.0,
        high = daily?.get(0)?.temp?.max ?: 0.0,
        low = daily?.get(0)?.temp?.min ?: 0.0,
        pressure = current?.pressure ?: 0,
        humidity = current?.humidity ?: 0,
        uvIndex = current?.uvIndex ?: 0.0,
        clouds = current?.clouds ?: 0,
        visibility = current?.visibility ?: 0,
        windSpeed = current?.windSpeed ?: 0.0,
        windDeg = current?.windDeg ?: 0,
        pop = current?.pop ?: 0.0,
        description = current?.weather?.get(0)?.description ?: "N/A",
        icon = current?.weather?.get(0)?.icon ?: "unknown",
        hourlyList = hourly?.map {
            HourlyDomainModel(
                epochTime = it.epochTime ?: 0,
                temperature = it.temp ?: 0.0,
                icon = it.weather?.get(0)?.icon ?: "unknown",
                pop = it.pop ?: 0.0
            )
        } ?: listOf(),
        dailyList = daily?.map {
            DailyDomainModel(
                epochTime = it.epochTime ?: 0,
                high = it.temp?.max ?: 0.0,
                low = it.temp?.min ?: 0.0,
                icon = it.weather?.get(0)?.icon ?: "unknown",
                pop = it.pop ?: 0.0
            )
        } ?: listOf()
    )
