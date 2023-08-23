package com.trianglz.weatherapp.presentation.navigation

sealed class Destinations(val route: String) {
    object Home : Destinations("Home")
    object ScreenTwo : Destinations("ScreenTwo")
}
