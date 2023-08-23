package com.trianglz.weatherapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trianglz.weatherapp.presentation.navigation.Destinations
import com.trianglz.weatherapp.presentation.ui.theme.WeatherAppTheme
/**
 * @param currentRoute represents the route of the currently previewed screen
 * @param navigateToHome is a call back to be implemented at the callers sight to manage navigation to home screen
 * @param navigateToScreenTwo is a call back to be implemented at the callers sight to manage navigation to the second screen
 * @param closeDrawer is a call back to be implemented at the callers sight to manage the sheet's retracting
 * */
@Composable
fun WeatherNavDrawerSheet(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToScreenTwo: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalDrawerSheet(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Weather App", style = MaterialTheme.typography.headlineLarge)
        }

        Divider(Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
        NavigationDrawerItem(
            label = { Text("Home") },
            icon = { Icon(imageVector = Icons.Rounded.Home, contentDescription = null) },
            selected = currentRoute == Destinations.Home.route,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer)
        )
        Divider(Modifier.padding(horizontal = 32.dp, vertical = 2.dp))
        NavigationDrawerItem(
            label = { Text("Screen Two") },
            icon = { Icon(imageVector = Icons.Rounded.Favorite, contentDescription = null) },
            selected = currentRoute == Destinations.ScreenTwo.route,
            onClick = { navigateToScreenTwo(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
            colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = MaterialTheme.colorScheme.tertiaryContainer)
        )
    }
}

@Preview
@Composable
fun WeatherNavDrawerSheetPreview() {
    WeatherAppTheme {
        var currentRoute by remember {
            mutableStateOf(Destinations.Home.route)
        }
        WeatherNavDrawerSheet(
            currentRoute,
            { currentRoute = Destinations.Home.route },
            { currentRoute = Destinations.ScreenTwo.route },
            {})
    }
}