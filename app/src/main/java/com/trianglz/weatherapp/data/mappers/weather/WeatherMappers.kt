package com.trianglz.weatherapp.data.mappers.weather

import com.trianglz.weatherapp.data.models.city.CityDto
import com.trianglz.weatherapp.data.models.weather.WeatherDto
import com.trianglz.weatherapp.domain.models.weather.Weather

fun WeatherDto.toWeather(city: CityDto): Weather =
    Weather(
        countryCode = city.country,
        cityName = city.name,
        currentTemperature = this.currentTemperature,
        feelsLike = this.feelsLike,
        lowTemperature = this.lowTemperature,
        highTemperature = this.highTemperature,
        humidityPercentage = this.humidityPercentage,
        cloudPercentage = this.cloudPercentage,
        windSpeed = this.windSpeed,
        windDegree = this.windDegree,
        sunrise = this.sunrise,
        sunset = this.sunset,
    )
