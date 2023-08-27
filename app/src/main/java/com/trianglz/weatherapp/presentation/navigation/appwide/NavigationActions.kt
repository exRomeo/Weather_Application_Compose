package com.trianglz.weatherapp.presentation.navigation.appwide

import androidx.navigation.NavController
import com.google.gson.Gson
import com.trianglz.weatherapp.presentation.models.weathercard.WeatherUiModel

/**
 * [NavigationActions] was created to encapsulate the navigation logic between the screens
 * and separate the inner screens navigation from the drawer navigation
 */

class NavigationActions(navController: NavController) {

    /**
     * [popup] pops up the current screen from the back stack
     */

    val popup: () -> Unit = {
        navController.popBackStack()
    }

    /**
     * [navigateToDetailsScreen] navigates to the details screen with the weather data
     * */

    val navigateToDetailsScreen: (weather: WeatherUiModel) -> Unit = {
        navController
            .navigate(route = Destinations.DetailsScreen.route + "/" + serializeObject(it)) {

                launchSingleTop = true


            }
    }


    private fun serializeObject(o: Any): String {
        return Gson().toJson(o)
    }

    inline fun <reified T> deserializeObject(o: String): T {
        return Gson().fromJson(o, T::class.java)
    }
}