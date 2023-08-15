package com.trianglz.weatherapp.data.mappers.country

import com.trianglz.weatherapp.data.models.country.CountryDataModel
import com.trianglz.weatherapp.domain.models.country.CountryDomainModel

fun CountryDataModel.toDomainModel(): CountryDomainModel =
    CountryDomainModel(
        name = name.common,
        code = code,
        flag = flags.png
    )