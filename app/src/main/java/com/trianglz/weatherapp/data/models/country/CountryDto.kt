package com.trianglz.weatherapp.data.models.country

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @field:SerializedName("name")
    val name: Name,
    @field:SerializedName("cca2")
    val code: String,
    @field:SerializedName("flag")
    val flag: String,
    @field:SerializedName("flags")
    val flags: Flags
)

data class Name(
    @field:SerializedName("common")
    val common: String,
    @field:SerializedName("official")
    val official: String,
)

data class Flags(
    @field:SerializedName("png")
    val png: String,
    @field:SerializedName("svg")
    val svg: String,
    @field:SerializedName("alt")
    val alt: String
)






