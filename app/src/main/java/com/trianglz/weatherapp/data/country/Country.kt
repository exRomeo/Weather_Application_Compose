package com.trianglz.weatherapp.data.country

import com.google.gson.annotations.SerializedName

data class Country(
    @field:SerializedName("iso2")
    val iso2: String,
    @field:SerializedName("name")
    val name: String
) {

}
