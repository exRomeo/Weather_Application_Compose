package com.trianglz.weatherapp.data.country

import com.google.gson.annotations.SerializedName

data class Country(
    @field:SerializedName("iso2")
    val iso2: String,
    @field:SerializedName("name")
    val name: String
) {
    companion object {
        fun getDummyCountries(): List<Country> = listOf(
            Country("EG", "Egypt"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("US", "United States"),
            Country("ET", "Ethiopia")
        )
    }
}
