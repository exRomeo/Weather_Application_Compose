package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.data.mappers.country.toDomainModel
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.domain.repository.Repository
import com.trianglz.weatherapp.domain.utils.UtilityManager
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


class CountrySearchUseCaseImpl @Inject constructor(
    private val repository: Repository,
    private val utilityManager: UtilityManager
) : CountrySearchUseCase {
    override suspend fun getCountries(
        countryName: String
    ): Result<List<CountryDomainModel>> = coroutineScope {
        try {
            Result.success(
                repository.getCountries(
                    countryName = countryName
                ).map { it.toDomainModel() }
            )

        } catch (exception: Exception) {
            Result.failure(Exception(utilityManager.handleException(exception)))
        }
    }
}