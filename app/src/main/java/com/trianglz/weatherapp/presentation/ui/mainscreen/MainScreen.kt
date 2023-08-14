package com.trianglz.weatherapp.presentation.ui.mainscreen

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.trianglz.weatherapp.presentation.navigation.Destinations
import com.trianglz.weatherapp.presentation.navigation.WeatherAppNavGraph
import com.trianglz.weatherapp.presentation.navigation.WeatherAppNavigationActions
import com.trianglz.weatherapp.presentation.ui.components.WeatherNavDrawerSheet
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

@Composable
fun WeatherApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        WeatherAppNavigationActions(navController)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: Destinations.Home.route

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            WeatherNavDrawerSheet(
                currentRoute = currentRoute,
                navigateToHome = navigationActions.navigateToHome,
                navigateToScreenTwo = navigationActions.navigateToScreenTwo,
                closeDrawer = { coroutineScope.launch { drawerState.close() } })
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        WeatherAppNavGraph(
            modifier = modifier,
            navController = navController,
            openDrawer = { coroutineScope.launch { drawerState.open() } }
        )
    }
}


@Preview
@Composable
fun WeatherAppPreview() {
    WeatherAppTheme {
        WeatherApp()
    }
}