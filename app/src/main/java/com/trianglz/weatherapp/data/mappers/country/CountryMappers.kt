package com.trianglz.weatherapp.data.mappers.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

/**
 * [toDomainModel] as its name suggests it maps [CountryDataModel] to [CountryDomainModel]
 * leaving behind all unnecessary fields
 * */
fun CountryDataModel.toDomainModel(): CountryDomainModel =
    CountryDomainModel(
        name = name.common,
        code = code,
        flag = flags.png
    )