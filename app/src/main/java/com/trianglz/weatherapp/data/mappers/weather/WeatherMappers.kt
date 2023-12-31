package com.trianglz.weatherapp.data.mappers.weather

import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.weather.WeatherDataModel
import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel

/**
 * [toDomainModel] as its name suggests maps [WeatherDataModel] to [WeatherDomainModel]
 * @param city adds to the weather data two necessary fields which aren't provided by the api "city name" and "country code"
 * */
fun WeatherDataModel.toDomainModel(city: CityDataModel): WeatherDomainModel =
    WeatherDomainModel(
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
