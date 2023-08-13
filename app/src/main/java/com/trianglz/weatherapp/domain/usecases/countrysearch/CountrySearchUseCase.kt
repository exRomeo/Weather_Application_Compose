package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.data.mappers.country.toCountry
import com.trianglz.weatherapp.domain.models.country.Country
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class CountrySearchUseCase @Inject constructor(
    private val repository: IRepository,
    private val utilityManager: IUtilityManager
) : ICountrySearchUseCase {
    override suspend fun getCountries(
        countryName: String
    ): Result<List<Country>> = coroutineScope {
        try {
            Result.success(
                repository.getCountries(
                    countryName = countryName
                ).map { it.toCountry() }
            )

        } catch (exception: Exception) {
            Result.failure(Exception(utilityManager.handleException(exception)))
        }
    }
}