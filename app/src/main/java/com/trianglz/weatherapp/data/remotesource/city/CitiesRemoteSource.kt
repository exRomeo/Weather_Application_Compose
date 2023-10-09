package com.trianglz.weatherapp.data.remotesource.city

import arrow.core.Either
import com.trianglz.weatherapp.data.models.city.CityDataModel
import com.trianglz.weatherapp.data.models.errors.WeatherAppErrorDataModel

interface CitiesRemoteSource {

    /**
     * [getCities] fetches cities that exists in a certain country
     * @param countryCode represents the country
     * @param limit represents the maximum number of cities to get from the api must be between 1 and 30 limited by the api
     * for more info about the api visit the [official site](https://api-ninjas.com/api/city)
     * */

    suspend fun getCities(
        countryCode: String,
        limit: Int
    ): Either<WeatherAppErrorDataModel, List<CityDataModel>>
}