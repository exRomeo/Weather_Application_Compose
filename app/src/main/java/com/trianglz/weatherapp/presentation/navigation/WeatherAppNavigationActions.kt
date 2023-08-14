package com.trianglz.weatherapp.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class WeatherAppNavigationActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(Destinations.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToScreenTwo: () -> Unit = {
        navController.navigate(Destinations.ScreenTwo.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}