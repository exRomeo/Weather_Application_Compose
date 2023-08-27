package com.trianglz.weatherapp.presentation.navigation.drawer

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination


/**
 * [DrawerNavigationActions] was created to encapsulate the navigation logic between the screens
 * in one place to make it easier to trace navigation issues
 * */

class DrawerNavigationActions(navController: NavController) {

    /**
     * [navigateToHome] and [navigateToScreenTwo] navigate to the destination screen and
     * saves the current screen state like data previewed and scroll position
     * it also makes sure to avoid having duplicates of the same screen in the back stack
     * */

    val navigateToHome: () -> Unit = {
        navController.navigate(route = DrawerDestinations.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


    val navigateToScreenTwo: () -> Unit = {
        navController.navigate(route = DrawerDestinations.ScreenTwo.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}