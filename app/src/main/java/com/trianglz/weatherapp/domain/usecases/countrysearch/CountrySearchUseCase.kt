package com.trianglz.weatherapp.domain.usecases.countrysearch

import com.trianglz.weatherapp.data.models.country.Country
import com.trianglz.weatherapp.domain.repository.IRepository
import com.trianglz.weatherapp.domain.utils.IUtilityManager
import com.trianglz.weatherapp.domain.utils.resource.Resource
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CountrySearchUseCase @Inject constructor(
    private val repository: IRepository,
    private val utilityManager: IUtilityManager
) : ICountrySearchUseCase {
    override suspend fun getCountries(
        countryName: String
    ): Resource<List<Country>> = coroutineScope {
        if (utilityManager.isInternetAvailable())
            try {
                Resource.Success(
                    repository.getCountries(
                        countryName = countryName
                    )
                )
            } catch (exception: Exception) {
                Resource.Error(utilityManager.handleException(exception))
            }
        else Resource.Error("Please, check your internet connection")
    }
}