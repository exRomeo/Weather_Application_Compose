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
import com.trianglz.weatherapp.presentation.navigation.appwide.NavigationActions
import com.trianglz.weatherapp.presentation.navigation.drawer.DrawerDestinations
import com.trianglz.weatherapp.presentation.navigation.drawer.DrawerNavGraph
import com.trianglz.weatherapp.presentation.navigation.drawer.DrawerNavigationActions
import com.trianglz.weatherapp.presentation.ui.components.WeatherNavDrawerSheet
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

@Composable
fun DrawerHost(
    modifier: Modifier = Modifier,
    navigationActions: NavigationActions
) {
    val navController = rememberNavController()
    val drawerNavigationActions = remember(navController) {
        DrawerNavigationActions(navController)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: DrawerDestinations.Home.route

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            WeatherNavDrawerSheet(
                currentRoute = currentRoute,
                navigateToHome = drawerNavigationActions.navigateToHome,
                navigateToScreenTwo = drawerNavigationActions.navigateToScreenTwo,
                closeDrawer = { coroutineScope.launch { drawerState.close() } })
        },
        drawerState = drawerState,
        gesturesEnabled = true
    ) {
        DrawerNavGraph(
            modifier = modifier,
            navController = navController,
            navigationActions = navigationActions,
            openDrawer = { coroutineScope.launch { drawerState.open() } }
        )
    }
}


@Preview
@Composable
fun WeatherAppPreview() {
    WeatherAppTheme {
        DrawerHost(navigationActions = NavigationActions(rememberNavController()))
    }
}