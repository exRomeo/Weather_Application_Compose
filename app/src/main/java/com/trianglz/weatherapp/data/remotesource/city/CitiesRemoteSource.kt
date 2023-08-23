package com.trianglz.weatherapp.data.remotesource.city

import com.trianglz.weatherapp.data.models.city.CityDataModel

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
    ): List<CityDataModel>
}