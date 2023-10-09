package com.trianglz.weatherapp.domain.repository.country

import arrow.core.Either
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel

interface CountriesRepository {

    suspend fun getCountries(
        countryName: String
    ): Either<WeatherAppErrorDomainModel, List<CountryDomainModel>>

}