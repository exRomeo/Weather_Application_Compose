package com.trianglz.weatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.ui.home.HomeScreen
import com.trianglz.weatherapp.presentation.ui.screentwo.ScreenTwo

@Composable
fun WeatherAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    openDrawer: () -> Unit = {}
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Home.route
    ) {

        composable(
            route = Destinations.Home.route
        ) {
            HomeScreen(modifier, openDrawer)
        }

        composable(
            route = Destinations.ScreenTwo.route
        ) {
            ScreenTwo()
        }
    }
}
