package com.trianglz.weatherapp.presentation.navigation.drawer

sealed class DrawerDestinations(val route: String) {

    object Home : DrawerDestinations("Home")

    object ScreenTwo : DrawerDestinations("ScreenTwo")

}
