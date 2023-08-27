package com.trianglz.weatherapp.domain.repository.weatherdetails

import com.trianglz.weatherapp.domain.models.weather.WeatherDomainModel
import com.trianglz.weatherapp.domain.models.weatherdetails.WeatherDetailsDomainModel

interface WeatherDetailsRepository {

    suspend fun oneCall(
        weatherDomainModel: WeatherDomainModel,
        lang: String,
        unit: String,
        exclude: String
    ): WeatherDetailsDomainModel

}