package com.trianglz.weatherapp.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.ui.home.HomeScreen

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
            Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "TODO SCREEN TWO",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}