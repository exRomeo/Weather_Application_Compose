package com.trianglz.weatherapp.presentation.navigation.appwide

sealed class Destinations(val route: String) {
    object Main : Destinations("Main")

    object DetailsScreen : Destinations("DetailsScreen")

}
