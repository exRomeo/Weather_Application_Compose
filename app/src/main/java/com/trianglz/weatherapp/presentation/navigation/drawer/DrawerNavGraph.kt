package com.trianglz.weatherapp.presentation.navigation.drawer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.navigation.appwide.NavigationActions
import com.trianglz.weatherapp.presentation.ui.home.HomeScreen
import com.trianglz.weatherapp.presentation.ui.screentwo.ScreenTwo

@Composable
fun DrawerNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions,
    openDrawer: () -> Unit = {}
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DrawerDestinations.Home.route
    ) {

        composable(
            route = DrawerDestinations.Home.route
        ) {
            HomeScreen(
                openDrawer = openDrawer,
                navigateToDetails = navigationActions.navigateToDetailsScreen
            )
        }

        composable(
            route = DrawerDestinations.ScreenTwo.route
        ) {
            ScreenTwo()
        }

    }
}



