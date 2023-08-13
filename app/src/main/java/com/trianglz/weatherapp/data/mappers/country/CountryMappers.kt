package com.trianglz.weatherapp.data.mappers.country

import com.trianglz.weatherapp.data.models.country.CountryDto
import com.trianglz.weatherapp.domain.models.country.Country

fun CountryDto.toCountry(): Country =
    Country(
        name = name.common,
        code = code,
        flag = flags.png
    )