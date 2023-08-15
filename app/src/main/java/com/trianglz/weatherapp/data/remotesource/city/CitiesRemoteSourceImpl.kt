package com.trianglz.weatherapp.data.remotesource.city

import com.trianglz.weatherapp.data.apiservice.apininja.ApiNinja
import javax.inject.Inject

class CitiesRemoteSourceImpl @Inject constructor(
    private val apiNinja: ApiNinja): CitiesRemoteSource {
}