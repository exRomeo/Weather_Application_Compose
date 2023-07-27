package com.trianglz.weatherapp.data.city

import com.google.gson.annotations.SerializedName

data class City(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("country")
    val country: String,
    @field:SerializedName("latitude")
    val latitude: Double,
    @field:SerializedName("longitude")
    val longitude: Double
)