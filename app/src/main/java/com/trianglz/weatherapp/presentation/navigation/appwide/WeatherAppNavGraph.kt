package com.trianglz.weatherapp.presentation.navigation.appwide

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.trianglz.weatherapp.presentation.ui.details.DetailsScreen
import com.trianglz.weatherapp.presentation.ui.mainscreen.DrawerHost

@Composable
fun WeatherAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destinations.Main.route
    ) {

        composable(route = Destinations.Main.route) {
            DrawerHost(modifier = modifier, navigationActions = navigationActions)
        }

        composable(
            route = Destinations.DetailsScreen.route + "/{weather}",
            arguments = listOf(
                navArgument("weather") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("weather")
                ?.let { weather ->
                    DetailsScreen(
                        navigationActions = navigationActions,
                        weather = navigationActions.deserializeObject(weather)
                    )
                }
        }

    }

}