package com.trianglz.weatherapp.presentation.mappers.country

import com.trianglz.weatherapp.domain.models.country.CountryDomainModel
import com.trianglz.weatherapp.presentation.models.result.ResultUiModel

fun ResultUiModel.toDomainModel(): CountryDomainModel =
    CountryDomainModel(
        name = name,
        code = code,
        flag = flag
    )

fun CountryDomainModel.toUiModel(): ResultUiModel =
    ResultUiModel(
        name = name,
        code = code,
        flag = flag
    )