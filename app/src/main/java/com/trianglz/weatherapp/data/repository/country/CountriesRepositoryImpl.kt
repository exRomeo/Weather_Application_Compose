package com.trianglz.weatherapp.data.repository.country

import arrow.core.Either
import com.trianglz.weatherapp.data.mappers.country.toDomainModel
import com.trianglz.weatherapp.data.mappers.errors.toDomainModel
import com.trianglz.weatherapp.data.remotesource.country.CountriesRemoteSource
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.models.errors.WeatherAppErrorDomainModel
import com.trianglz.weatherapp.domain.repository.country.CountriesRepository
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val dataSource: CountriesRemoteSource
) : CountriesRepository {

    override suspend fun getCountries(
        countryName: String
    ): Either<WeatherAppErrorDomainModel, List<CountryDomainModel>> =
        dataSource.getCountries(
            countryName = countryName
        ).mapLeft { it.toDomainModel() }
            .map { countryList -> countryList.map { it.toDomainModel() } }
}