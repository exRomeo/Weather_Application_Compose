package com.trianglz.weatherapp.presentation.mappers.country

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.presentation.models.country.CountryUiModel

fun CountryUiModel.toDomainModel(): CountryDomainModel =
    CountryDomainModel(
        name = name,
        code = code,
        flag = flag
    )

fun CountryDomainModel.toUiModel(): CountryUiModel =
    CountryUiModel(
        name = name,
        code = code,
        flag = flag
    )